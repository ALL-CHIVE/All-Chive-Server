package allchive.server.infrastructure.slack;

import static com.slack.api.model.block.Blocks.divider;
import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;

import allchive.server.core.event.events.slack.SlackAsyncErrorEvent;
import allchive.server.core.event.events.slack.SlackErrorEvent;
import com.slack.api.model.block.Blocks;
import com.slack.api.model.block.DividerBlock;
import com.slack.api.model.block.HeaderBlock;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import com.slack.api.model.block.composition.TextObject;
import com.slack.api.webhook.Payload;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.ServletInputStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class SlackMessageGenerater {
    private final int MAX_LEN = 500;

    public Payload generateErrorMsg(SlackErrorEvent event) throws IOException {
        final Exception e = event.getException();
        final ContentCachingRequestWrapper cachedRequest = event.getCachedRequest();

        List<LayoutBlock> layoutBlocks = new ArrayList<>();
        // 제목
        layoutBlocks.add(HeaderBlock.builder().text(plainText("에러 알림")).build());
        layoutBlocks.add(new DividerBlock());
        // time, Addr
        layoutBlocks.add(makeSection(getTime(), getAddr(cachedRequest)));
        // RequestBody + RequestParam
        layoutBlocks.add(makeSection(getBody(cachedRequest), getParam(cachedRequest)));
        layoutBlocks.add(new DividerBlock());
        layoutBlocks.add(makeSection(getErrMessage(e), getErrStack(e)));

        return Payload.builder().text("에러 알림").blocks(layoutBlocks).build();
    }

    private LayoutBlock getTimeBlock() {
        MarkdownTextObject timeObj =
                MarkdownTextObject.builder().text("* Time :*\n" + LocalDateTime.now()).build();
        return Blocks.section(section -> section.fields(List.of(timeObj)));
    }

    private MarkdownTextObject getTime() {
        return MarkdownTextObject.builder().text("* Time :*\n" + LocalDateTime.now()).build();
    }

    private LayoutBlock makeSection(TextObject first, TextObject second) {
        return Blocks.section(section -> section.fields(List.of(first, second)));
    }

    private MarkdownTextObject getErrMessage(Exception e) {
        final String errorMessage = e.getMessage();
        return MarkdownTextObject.builder().text("* Message :*\n" + errorMessage).build();
    }

    private MarkdownTextObject getErrStack(Throwable throwable) {
        String exceptionAsString = Arrays.toString(throwable.getStackTrace());
        int cutLength = Math.min(exceptionAsString.length(), MAX_LEN);
        String errorStack = exceptionAsString.substring(0, cutLength);
        return MarkdownTextObject.builder().text("* Stack Trace :*\n" + errorStack).build();
    }

    private MarkdownTextObject getAddr(ContentCachingRequestWrapper c) {
        final String method = c.getMethod();
        final String url = c.getRequestURL().toString();
        return MarkdownTextObject.builder()
                .text("* Request Addr :*\n" + method + " : " + url)
                .build();
    }

    private MarkdownTextObject getBody(ContentCachingRequestWrapper c) throws IOException {
        ServletInputStream inputStream = c.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        return MarkdownTextObject.builder().text("* Request Body :*\n" + messageBody).build();
    }

    private MarkdownTextObject getParam(ContentCachingRequestWrapper c) {
        final String param = c.getQueryString();
        return MarkdownTextObject.builder().text("* Request Param :*\n" + param).build();
    }

    public Payload generateAsyncErrorMsg(SlackAsyncErrorEvent event) {
        String name = event.getName();
        Throwable throwable = event.getThrowable();
        Object[] params = event.getParams();
        List<LayoutBlock> layoutBlocks = new ArrayList<>();
        layoutBlocks.add(
                Blocks.header(
                        headerBlockBuilder -> headerBlockBuilder.text(plainText("비동기 에러 알림"))));
        layoutBlocks.add(divider());

        MarkdownTextObject errorUserIdMarkdown =
                MarkdownTextObject.builder().text("* 메소드 이름 :*\n" + name).build();
        MarkdownTextObject errorUserIpMarkdown =
                MarkdownTextObject.builder()
                        .text("* 요청 파라미터 :*\n" + getParamsToString(params))
                        .build();
        layoutBlocks.add(
                section(
                        section ->
                                section.fields(List.of(errorUserIdMarkdown, errorUserIpMarkdown))));

        layoutBlocks.add(divider());
        layoutBlocks.add(getTimeBlock());
        String errorStack = getErrorStack(throwable);
        String message = throwable.toString();
        MarkdownTextObject errorNameMarkdown =
                MarkdownTextObject.builder().text("* Message :*\n" + message).build();
        MarkdownTextObject errorStackMarkdown =
                MarkdownTextObject.builder().text("* Stack Trace :*\n" + errorStack).build();
        layoutBlocks.add(
                section(section -> section.fields(List.of(errorNameMarkdown, errorStackMarkdown))));
        return Payload.builder().text("비동기 에러 알림").blocks(layoutBlocks).build();
    }

    private String getParamsToString(Object[] params) {
        StringBuilder paramToString = new StringBuilder();
        for (Object param : params) {
            paramToString.append(param.toString());
        }
        return paramToString.toString();
    }

    private String getErrorStack(Throwable throwable) {
        String exceptionAsString = Arrays.toString(throwable.getStackTrace());
        int cutLength = Math.min(exceptionAsString.length(), MAX_LEN);
        return exceptionAsString.substring(0, cutLength);
    }
}

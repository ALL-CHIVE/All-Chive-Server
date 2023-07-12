package allchive.server.domain.domains.content.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "tbl_content_tag_group")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentTagGroup extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;

    @Builder
    private ContentTagGroup(Content content, Tag tag) {
        this.content = content;
        this.tag = tag;
    }

    public static ContentTagGroup of(Content content, Tag tag) {
        return ContentTagGroup.builder().content(content).tag(tag).build();
    }
}

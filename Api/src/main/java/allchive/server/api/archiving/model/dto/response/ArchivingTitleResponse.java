package allchive.server.api.archiving.model.dto.response;


import allchive.server.api.archiving.model.vo.TitleContentCntVo;
import allchive.server.api.common.json.naming.UpperCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(UpperCaseStrategy.class)
public class ArchivingTitleResponse {
    private List<TitleContentCntVo> FOOD;
    private List<TitleContentCntVo> LIFE;
    private List<TitleContentCntVo> HOME_LIVING;
    private List<TitleContentCntVo> SHOPPING;
    private List<TitleContentCntVo> SPORT;
    private List<TitleContentCntVo> SELF_IMPROVEMENT;
    private List<TitleContentCntVo> TECH;
    private List<TitleContentCntVo> DESIGN;
    private List<TitleContentCntVo> TREND;

    @Builder
    private ArchivingTitleResponse(
            List<TitleContentCntVo> FOOD,
            List<TitleContentCntVo> LIFE,
            List<TitleContentCntVo> HOME_LIVING,
            List<TitleContentCntVo> SHOPPING,
            List<TitleContentCntVo> SPORT,
            List<TitleContentCntVo> SELF_IMPROVEMENT,
            List<TitleContentCntVo> TECH,
            List<TitleContentCntVo> DESIGN,
            List<TitleContentCntVo> TREND) {
        this.FOOD = FOOD;
        this.LIFE = LIFE;
        this.HOME_LIVING = HOME_LIVING;
        this.SHOPPING = SHOPPING;
        this.SPORT = SPORT;
        this.SELF_IMPROVEMENT = SELF_IMPROVEMENT;
        this.TECH = TECH;
        this.DESIGN = DESIGN;
        this.TREND = TREND;
    }

    public static ArchivingTitleResponse init() {
        return ArchivingTitleResponse.builder()
                .FOOD(new ArrayList<>())
                .LIFE(new ArrayList<>())
                .HOME_LIVING(new ArrayList<>())
                .SHOPPING(new ArrayList<>())
                .SPORT(new ArrayList<>())
                .SELF_IMPROVEMENT(new ArrayList<>())
                .TECH(new ArrayList<>())
                .DESIGN(new ArrayList<>())
                .TREND(new ArrayList<>())
                .build();
    }

    public void addFood(TitleContentCntVo vo) {
        this.FOOD.add(vo);
    }

    public void addLife(TitleContentCntVo vo) {
        this.LIFE.add(vo);
    }

    public void addHomeLiving(TitleContentCntVo vo) {
        this.HOME_LIVING.add(vo);
    }

    public void addShopping(TitleContentCntVo vo) {
        this.SHOPPING.add(vo);
    }

    public void addSport(TitleContentCntVo vo) {
        this.SPORT.add(vo);
    }

    public void addSelfImprovement(TitleContentCntVo vo) {
        this.SELF_IMPROVEMENT.add(vo);
    }

    public void addTech(TitleContentCntVo vo) {
        this.TECH.add(vo);
    }

    public void addDesign(TitleContentCntVo vo) {
        this.DESIGN.add(vo);
    }

    public void addTrend(TitleContentCntVo vo) {
        this.TREND.add(vo);
    }
}

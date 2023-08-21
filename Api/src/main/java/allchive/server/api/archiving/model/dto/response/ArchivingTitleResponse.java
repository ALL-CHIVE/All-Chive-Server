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
    private List<TitleContentCntVo> food;
    private List<TitleContentCntVo> life;
    private List<TitleContentCntVo> home_living;
    private List<TitleContentCntVo> shopping;
    private List<TitleContentCntVo> sport;
    private List<TitleContentCntVo> self_improvement;
    private List<TitleContentCntVo> tech;
    private List<TitleContentCntVo> design;
    private List<TitleContentCntVo> trend;
    private List<TitleContentCntVo> etc;

    @Builder
    private ArchivingTitleResponse(
            List<TitleContentCntVo> food,
            List<TitleContentCntVo> life,
            List<TitleContentCntVo> home_living,
            List<TitleContentCntVo> shopping,
            List<TitleContentCntVo> sport,
            List<TitleContentCntVo> self_improvement,
            List<TitleContentCntVo> tech,
            List<TitleContentCntVo> design,
            List<TitleContentCntVo> trend,
            List<TitleContentCntVo> etc) {
        this.food = food;
        this.life = life;
        this.home_living = home_living;
        this.shopping = shopping;
        this.sport = sport;
        this.self_improvement = self_improvement;
        this.tech = tech;
        this.design = design;
        this.trend = trend;
        this.etc = etc;
    }

    public static ArchivingTitleResponse init() {
        return ArchivingTitleResponse.builder()
                .food(new ArrayList<>())
                .life(new ArrayList<>())
                .home_living(new ArrayList<>())
                .shopping(new ArrayList<>())
                .sport(new ArrayList<>())
                .self_improvement(new ArrayList<>())
                .tech(new ArrayList<>())
                .design(new ArrayList<>())
                .trend(new ArrayList<>())
                .etc(new ArrayList<>())
                .build();
    }

    public void addFood(TitleContentCntVo vo) {
        this.food.add(vo);
    }

    public void addLife(TitleContentCntVo vo) {
        this.life.add(vo);
    }

    public void addHomeLiving(TitleContentCntVo vo) {
        this.home_living.add(vo);
    }

    public void addShopping(TitleContentCntVo vo) {
        this.shopping.add(vo);
    }

    public void addSport(TitleContentCntVo vo) {
        this.sport.add(vo);
    }

    public void addSelfImprovement(TitleContentCntVo vo) {
        this.self_improvement.add(vo);
    }

    public void addTech(TitleContentCntVo vo) {
        this.tech.add(vo);
    }

    public void addDesign(TitleContentCntVo vo) {
        this.design.add(vo);
    }

    public void addTrend(TitleContentCntVo vo) {
        this.trend.add(vo);
    }

    public void addEtc(TitleContentCntVo vo) {
        this.etc.add(vo);
    }
}

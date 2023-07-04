package allchive.server.api.category.model.dto.response;


import allchive.server.api.category.model.vo.TitleContentCntVo;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryTitleResponse {
    private List<TitleContentCntVo> food;
    private List<TitleContentCntVo> life;
    private List<TitleContentCntVo> homeLiving;
    private List<TitleContentCntVo> shopping;
    private List<TitleContentCntVo> sport;
    private List<TitleContentCntVo> selfImprovement;
    private List<TitleContentCntVo> tech;
    private List<TitleContentCntVo> design;
    private List<TitleContentCntVo> trend;

    @Builder
    private CategoryTitleResponse(
            List<TitleContentCntVo> food,
            List<TitleContentCntVo> life,
            List<TitleContentCntVo> homeLiving,
            List<TitleContentCntVo> shopping,
            List<TitleContentCntVo> sport,
            List<TitleContentCntVo> selfImprovement,
            List<TitleContentCntVo> tech,
            List<TitleContentCntVo> design,
            List<TitleContentCntVo> trend) {
        this.food = food;
        this.life = life;
        this.homeLiving = homeLiving;
        this.shopping = shopping;
        this.sport = sport;
        this.selfImprovement = selfImprovement;
        this.tech = tech;
        this.design = design;
        this.trend = trend;
    }

    public static CategoryTitleResponse init() {
        return CategoryTitleResponse.builder()
                .food(new ArrayList<>())
                .life(new ArrayList<>())
                .homeLiving(new ArrayList<>())
                .shopping(new ArrayList<>())
                .sport(new ArrayList<>())
                .selfImprovement(new ArrayList<>())
                .tech(new ArrayList<>())
                .design(new ArrayList<>())
                .trend(new ArrayList<>())
                .build();
    }

    public void addFood(TitleContentCntVo vo) {
        this.food.add(vo);
    }

    public void addLife(TitleContentCntVo vo) {
        this.life.add(vo);
    }

    public void addHomeLiving(TitleContentCntVo vo) {
        this.homeLiving.add(vo);
    }

    public void addShopping(TitleContentCntVo vo) {
        this.shopping.add(vo);
    }

    public void addSport(TitleContentCntVo vo) {
        this.sport.add(vo);
    }

    public void addSelfImprovement(TitleContentCntVo vo) {
        this.selfImprovement.add(vo);
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
}

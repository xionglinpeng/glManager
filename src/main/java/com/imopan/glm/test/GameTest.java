package com.imopan.glm.test;

import com.imopan.glm.entity.game.GameCentre;
import com.imopan.glm.entity.game.GamePioneer;
import com.imopan.glm.entity.game.GameType;
import com.imopan.glm.service.IGameService;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/1/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:springmvc.xml"})
public class GameTest {
    @Autowired
    private Datastore datastore;
    @Autowired
    private IGameService gameService;
    @Test
    public  void testinsertgamepioneer(){
        Query<GameCentre> query = datastore.find(GameCentre.class).filter("_id", new ObjectId("58744e822e3401385462cd65"));
        List<GameCentre> gameCentres = query.asList();
        GameCentre gameCentre = gameCentres.get(0);

        List<GamePioneer> pioneers=new ArrayList<GamePioneer>();
        for (int i=0;i<3;i++){
            GamePioneer gp=new GamePioneer();
            gp.setFinnum(i);
            gp.setPid(new ObjectId().toString());
            gp.setGname("王者荣耀");
            gp.setPname("王者荣耀尖兵任务"+i);
            pioneers.add(gp);
        }
        gameCentre.setPioneers(pioneers);
        gameCentre.setPutawayTime(new Date());
        datastore.save(gameCentre);

    }

    @Test

    public void initgametype() throws Exception {

        LinkedHashMap<String,Object> lm=new LinkedHashMap<String,Object>();
        lm.put("ACT","动作游戏");
        lm.put("AVG","冒险游戏");
        lm.put("FTG","格斗游戏");
        lm.put("FPS","第一视角");
        lm.put("RPG","角色扮演");
        lm.put("RTS","即时战略");
        lm.put("SLG","策略战旗");
        lm.put("SIM","模拟经营");
        lm.put("EDU","养成游戏");
        lm.put("SPT","体育游戏");
        lm.put("STG","射击游戏");
        lm.put("RAC","竞速游戏");
        lm.put("MUG","音乐游戏");
        lm.put("LVG","恋爱游戏");
        lm.put("PUZ","益智游戏");
        lm.put("CAG","卡牌游戏");
        lm.put("OTH","其他游戏");
        int i=1;
        for (String key : lm.keySet()) {
            GameType gt=new GameType();
            gt.setGtid(i+"");
            gt.setGtName(key);
            gt.setGtDesc(lm.get(key).toString());
            datastore.save(gt);
            i++;
        }
    }

    @Test
    public void testservice() throws Exception {
        GamePioneer gamePioneer = new GamePioneer();
        gamePioneer.setGid("58744e822e3401385462cd65");
        gamePioneer.setPid("58776142598bfd203ccec7e3");

        gameService.getGamePioneer(gamePioneer);
    }
}

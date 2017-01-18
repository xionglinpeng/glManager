package com.imopan.glm.service;

import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.bean.TableResult;
import com.imopan.glm.bean.TableSide;
import com.imopan.glm.entity.game.GameGoods;
import com.imopan.glm.entity.game.GamePioneer;
import com.imopan.glm.entity.game.ParamVo;

public interface IGameService {

	TableResult getGameList(ParamVo pv, TableSide tableSide);

	TableResult getGamePioneerList(ParamVo pv, TableSide tableSide);

	ResultBean doGamePioneer(GamePioneer paramVo);

	ResultBean getGamePioneer(GamePioneer paramVo);

	ResultBean doGamePioneerStatus(GamePioneer paramVo);

    TableResult getGameGiftList(ParamVo paramVo, TableSide tableSide);

    ResultBean getGiftById(GameGoods paramVo);

	ResultBean doGameGift(GameGoods paramVo);

	ResultBean doGameGiftStatus(GameGoods paramVo);
}

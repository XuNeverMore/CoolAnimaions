package com.nevermore.coolanimation.widget.rain;

import com.nevermore.coolanimation.widget.rain.raindrop.IRainDrop;

import java.util.List;

public interface ISummoner {

    interface IRaindropCreator{
        void injectRaindrops(ISummoner summoner);
    }


    void initRaindrops(List<IRainDrop> list);

    List<IRainDrop> getRaindrops();

    /**
     * start to rain
     */
    void fall();

    /**
     * stop the rain
     */
    void stop();


    void deployRaindrops();


    boolean shouldAbandon(IRainDrop rainDrop);

    void setRaindropCreator(IRaindropCreator raindropCreator);

    void setMaxRaindropCount(int maxRaindropCount);

    int getMaxRaindropCount();

}

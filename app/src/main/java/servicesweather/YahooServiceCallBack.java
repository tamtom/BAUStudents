package servicesweather;

import dataweather.Channel;

/**
 * Created by omar on 9/17/2015.
 */
public interface YahooServiceCallBack {
    void servicesucsess(Channel channel);

    void servicefail(Exception ex);

}

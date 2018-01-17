package com.example.wan.myjingdong.http;

import android.os.Handler;
import android.text.TextUtils;

import com.example.wan.myjingdong.callback.CallBack;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by WuXirui
 * Create Time: 2017/11/9
 * Description:
 */

public class HttpUtils {
    private static final String TAG = "HttpUtils";
    private static volatile HttpUtils instance;

    private static Handler handler = new Handler();

    private HttpUtils() {

    }

    public static HttpUtils getInstance() {
        if (null == instance) {
            synchronized (HttpUtils.class) {
                if (instance == null) {
                    instance = new HttpUtils();
                }
            }
        }
        return instance;
    }


    /**
     * Get请求
     *
     * @param url
     * @param map
     * @param callBack
     * @param cls
     * @param tag
     */
    public void get(String url, Map<String, String> map, final CallBack callBack,
                    final Class cls, final String tag) {

        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .get()
                .url(url.toString())
                .build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // 通过自己传进来的回调接口对象回传回去
                        callBack.onFailed(tag, e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                // 请求成功之后做解析，通过自己的回调接口将数据返回回去
//                Log.e(TAG, "onResponse: "+result );
//              {"msg":"","code":"0","data":[{"aid":1,"createtime":"2017-10-30T22:01:55","icon":"http:\/\/120.27.23.105\/images\/ad\/0.jpg","productId":null,"title":"花生油","type":0,"url":"http:\/\/m.mv14449315.icoc.bz\/index.jsp"},{"aid":2,"createtime":"2017-10-30T22:01:55","icon":"http:\/\/120.27.23.105\/images\/ad\/1.jpg","productId":null,"title":"京东女人节","type":0,"url":"http:\/\/m.mv14449315.icoc.bz\/index.jsp"},{"aid":3,"createtime":"2017-10-30T22:01:55","icon":"http:\/\/120.27.23.105\/images\/ad\/2.jpg","productId":null,"title":"国庆大惠战","type":0,"url":"http:\/\/m.mv14449315.icoc.bz\/index.jsp"},{"aid":4,"createtime":"2017-10-30T21:28:02","icon":"http:\/\/120.27.23.105\/images\/ad\/3.jpg","productId":"1","title":"北京稻香村 稻香村中秋节月饼 老北京月饼礼盒655g","type":1,"url":""}],"tuijian":{"list":[{"bargainPrice":11800.0,"createtime":"2017-10-10T17:33:37","detailUrl":"https:\/\/item.m.jd.com\/product\/4338107.html?utm#_source=androidapp&utm#_medium=appshare&utm#_campaign=t#_335139774&utm#_term=QQfriends","images":"https:\/\/m.360buyimg.com\/n0\/jfs\/t6700\/155\/2098998076\/156185\/6cf95035\/595dd5a5Nc3a7dab5.jpg!q70.jpg","itemtype":0,"pid":57,"price":5199.0,"pscid":40,"salenum":4343,"sellerid":1,"subhead":"【i5 MX150 2G显存】全高清窄边框 8G内存 256固态硬盘 支持指纹识别 预装WIN10系统","title":"小米(MI)Air 13.3英寸全金属轻薄笔记本(i5-7200U 8G 256G PCle SSD MX150 2G独显 FHD 指纹识别 Win10）银\r\n"},{"bargainPrice":11800.0,"createtime":"2017-10-14T21:38:26","detailUrl":"https:\/\/item.m.jd.com\/product\/5025518.html?utm#_source=androidapp&utm#_medium=appshare&utm#_campaign=t#_335139774&utm#_term=QQfriends","images":"https:\/\/m.360buyimg.com\/n0\/jfs\/t8830\/106\/1760940277\/195595\/5cf9412f\/59bf2ef5N5ab7dc16.jpg!q70.jpg|https:\/\/m.360buyimg.com\/n0\/jfs\/t5428\/70\/1520969931\/274676\/b644dd0d\/591128e7Nd2f70da0.jpg!q70.jpg|https:\/\/m.360buyimg.com\/n0\/jfs\/t5566\/365\/1519564203\/36911\/620c750c\/591128eaN54ac3363.jpg!q70.jpg","itemtype":1,"pid":58,"price":6399.0,"pscid":40,"salenum":545,"sellerid":2,"subhead":"升级4G大显存！Nvme协议Pcie SSD,速度快人一步】GTX1050Ti就选拯救者！专业游戏键盘&新模具全新设计！","title":"联想(Lenovo)拯救者R720 15.6英寸游戏笔记本电脑(i5-7300HQ 8G 1T+128G SSD GTX1050Ti 4G IPS 黑)"},{"bargainPrice":5599.0,"createtime":"2017-10-10T17:30:32","detailUrl":"https:\/\/item.m.jd.com\/product\/4824715.html?utm#_source=androidapp&utm#_medium=appshare&utm#_campaign=t#_335139774&utm#_term=QQfriends","images":"https:\/\/m.360buyimg.com\/n12\/jfs\/t7768\/184\/1153704394\/148460\/f42e1432\/599a930fN8a85626b.jpg!q70.jpg","itemtype":0,"pid":59,"price":5599.0,"pscid":40,"salenum":675,"sellerid":3,"subhead":"游戏本选择4G独显，拒绝掉帧】升级版IPS全高清防眩光显示屏，WASD方向键颜色加持，三大出风口立体散热！","title":"戴尔DELL灵越游匣15PR-6648B GTX1050 15.6英寸游戏笔记本电脑(i5-7300HQ 8G 128GSSD+1T 4G独显 IPS)黑"},{"bargainPrice":11800.0,"createtime":"2017-10-14T21:48:08","detailUrl":"https:\/\/mitem.jd.hk\/ware\/view.action?wareId=1988853309&cachekey=1acb07a701ece8d2434a6ae7fa6870a1","images":"https:\/\/m.360buyimg.com\/n0\/jfs\/t6130\/97\/1370670410\/180682\/1109582a\/593276b1Nd81fe723.jpg!q70.jpg|https:\/\/m.360buyimg.com\/n0\/jfs\/t5698\/110\/2617517836\/202970\/c9388feb\/593276b7Nbd94ef1f.jpg!q70.jpg|https:\/\/m.360buyimg.com\/n0\/jfs\/t5698\/110\/2617517836\/202970\/c9388feb\/593276b7Nbd94ef1f.jpg!q70.jpg|https:\/\/m.360buyimg.com\/n0\/jfs\/t5815\/178\/2614671118\/51656\/7f52d137\/593276c7N107b725a.jpg!q70.jpg|https:\/\/m.360buyimg.com\/n0\/jfs\/t5878\/60\/2557817477\/30873\/4502b606\/593276caN5a7d6357.jpg!q70.jpg","itemtype":2,"pid":60,"price":13888.0,"pscid":40,"salenum":466,"sellerid":4,"subhead":"购买电脑办公部分商品满1元返火车票5元优惠券（返完即止）","title":"全球购 新款Apple MacBook Pro 苹果笔记本电脑 银色VP2新13英寸Bar i5\/8G\/256G

                    handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Object o;
                        if (TextUtils.isEmpty(result)) {
                            o = null;
                        } else {
                            o = GsonUtils.getInstance().fromJson(result, cls);
                        }
                        callBack.onSuccess(tag, o);
                    }
                });
            }
        });


    }
}

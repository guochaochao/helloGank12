package com.gcc.hellogank.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 2017/4/19.
 */

public class VideoListBean {


    /**
     * code : 0
     * result : [{"type":"recommend","head":{"param":"","goto":"","style":"gm_av","title":"热门焦点"},"body":[{"title":"【潇洒一辣】国产动画=国漫？怕是失了智！","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/edf6330a984cfcda9a56aec91a5fd8bfd17ec3e8.jpg","param":"10396087","goto":"av","width":350,"height":219,"play":"7.7万","danmaku":"2211"},{"title":"【化妆师MK】我的夏季清爽四套穿搭！","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/c3e4c97ac046286c2c576a3fdc3fb6805c9cbe57.jpg","param":"10325126","goto":"av","width":350,"height":219,"play":"2.9万","danmaku":"205"},{"title":"【手书】【棋魂MAD】Message【2017.5.5】","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/f0233955266a2ecb24476ca0a75c8d6cd7d3fd1b.jpg","param":"10334880","goto":"av","width":350,"height":219,"play":"2.6万","danmaku":"399"},{"title":"【刷牙】[良心原创]《桥》  一首属于重庆自己的歌","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/803f0cb185816e3bf426903439440d147d345609.jpg","param":"10343954","goto":"av","width":350,"height":219,"play":"2933","danmaku":"446"}]},{"type":"live","head":{"param":"","goto":"live","style":"gm_av","title":"正在直播","count":3307},"body":[{"title":"【滚】周一摸鱼身体棒","style":"gm_live","cover":"http://i0.hdslb.com/bfs/live/64fa44391b12325f7c52a430b62833c6a795e42d.jpg","param":"5096","goto":"live","width":580,"height":364,"up_face":"http://i0.hdslb.com/bfs/face/a6df2031954cb5e1b5fa4a9b5f2d3ea1ed269c1b.jpg","up":"两仪滚","online":17098,"area":"单机联机","area_id":1},{"title":"(つД`)偷偷摸摸直播？！","style":"gm_live","cover":"http://i0.hdslb.com/bfs/live/a0b3b4976d5cef9a91a632222eb9df24118bb00b.jpg","param":"5279","goto":"live","width":580,"height":364,"up_face":"http://i2.hdslb.com/bfs/face/8c49a758216f9bd14b0046afe48a3514f44126f0.jpg","up":"宫本狗雨","online":15865,"area":"电子竞技","area_id":4},{"title":"你找到我啦！","style":"gm_live","cover":"http://i0.hdslb.com/bfs/live/75f692099ece6d29a0d3534ab7fe4751e4fb12fd.jpg","param":"280991","goto":"live","width":580,"height":364,"up_face":"http://i0.hdslb.com/bfs/face/58ce73df7a3a6b0e1926652af7a4ac0455dafc63.jpg","up":"草莓森森","online":100,"area":"网络游戏","area_id":3},{"title":"狸狸❉情缘？不存在的❉","style":"gm_live","cover":"http://i0.hdslb.com/bfs/live/0aa408250e2b2a85d28023439461743e419e86ef.jpg","param":"75181","goto":"live","width":580,"height":364,"up_face":"http://i2.hdslb.com/bfs/face/b532ff07339651708286529472d4bc6d33385a01.jpg","up":"周泽楷我老公","online":26,"area":"网络游戏","area_id":3}]},{"type":"bangumi_2","head":{"param":"13","goto":"subarea","style":"gs_bangumi","title":"番剧区"},"body":[{"title":"世界黑暗图鉴","style":"gs_bangumi","cover":"http://i1.hdslb.com/bfs/archive/7090b6548400a2fc6e6fb9d2c6894c715bbff359.jpg","param":"6011","goto":"bangumi_list","width":320,"height":422,"desc1":"更新到第6话","status":2},{"title":"小魔女学园 TV版","style":"gs_bangumi","cover":"http://i1.hdslb.com/bfs/archive/060a4bf2cf38f659c27b42264be31d471c2fd9e3.jpg","param":"5788","goto":"bangumi_list","width":320,"height":422,"desc1":"更新到第18话","status":2},{"title":"怪怪守护神","style":"gs_bangumi","cover":"http://i1.hdslb.com/bfs/archive/1e3b43b51d7807b9dfe309ea6bb30d58d25260aa.jpg","param":"5988","goto":"bangumi_list","width":320,"height":422,"desc1":"更新到第6话","status":2},{"title":"爱丽丝与藏六","style":"gs_bangumi","cover":"http://i2.hdslb.com/bfs/archive/0f1bc1bf921be3e3371605792337a1accb7c578a.jpg","param":"5995","goto":"bangumi_list","width":320,"height":422,"desc1":"更新到第SP话","status":2}]}]
     */

    private int code;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * type : recommend
         * head : {"param":"","goto":"","style":"gm_av","title":"热门焦点"}
         * body : [{"title":"【潇洒一辣】国产动画=国漫？怕是失了智！","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/edf6330a984cfcda9a56aec91a5fd8bfd17ec3e8.jpg","param":"10396087","goto":"av","width":350,"height":219,"play":"7.7万","danmaku":"2211"},{"title":"【化妆师MK】我的夏季清爽四套穿搭！","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/c3e4c97ac046286c2c576a3fdc3fb6805c9cbe57.jpg","param":"10325126","goto":"av","width":350,"height":219,"play":"2.9万","danmaku":"205"},{"title":"【手书】【棋魂MAD】Message【2017.5.5】","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/f0233955266a2ecb24476ca0a75c8d6cd7d3fd1b.jpg","param":"10334880","goto":"av","width":350,"height":219,"play":"2.6万","danmaku":"399"},{"title":"【刷牙】[良心原创]《桥》  一首属于重庆自己的歌","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/803f0cb185816e3bf426903439440d147d345609.jpg","param":"10343954","goto":"av","width":350,"height":219,"play":"2933","danmaku":"446"}]
         */

        private String type;
        private HeadBean head;
        private List<BodyBean> body;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public HeadBean getHead() {
            return head;
        }

        public void setHead(HeadBean head) {
            this.head = head;
        }

        public List<BodyBean> getBody() {
            return body;
        }

        public void setBody(List<BodyBean> body) {
            this.body = body;
        }

        public static class HeadBean {
            /**
             * param :
             * goto :
             * style : gm_av
             * title : 热门焦点
             */

            private String param;
            @SerializedName("goto")
            private String gotoX;
            private String style;
            private String title;

            public String getParam() {
                return param;
            }

            public void setParam(String param) {
                this.param = param;
            }

            public String getGotoX() {
                return gotoX;
            }

            public void setGotoX(String gotoX) {
                this.gotoX = gotoX;
            }

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class BodyBean {
            /**
             * title : 【潇洒一辣】国产动画=国漫？怕是失了智！
             * style : gm_av
             * cover : http://i0.hdslb.com/bfs/archive/edf6330a984cfcda9a56aec91a5fd8bfd17ec3e8.jpg
             * param : 10396087
             * goto : av
             * width : 350
             * height : 219
             * play : 7.7万
             * danmaku : 2211
             */

            private String title;
            private String style;
            private String cover;
            private String param;
            @SerializedName("goto")
            private String gotoX;
            private int width;
            private int height;
            private String play;
            private String danmaku;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getParam() {
                return param;
            }

            public void setParam(String param) {
                this.param = param;
            }

            public String getGotoX() {
                return gotoX;
            }

            public void setGotoX(String gotoX) {
                this.gotoX = gotoX;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getPlay() {
                return play;
            }

            public void setPlay(String play) {
                this.play = play;
            }

            public String getDanmaku() {
                return danmaku;
            }

            public void setDanmaku(String danmaku) {
                this.danmaku = danmaku;
            }
        }
    }
}

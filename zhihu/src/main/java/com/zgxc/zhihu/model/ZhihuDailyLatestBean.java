package com.zgxc.zhihu.model;


import java.util.List;

/**
 *
 * {
 "date": "20190327",
 "stories": [
 {
 "images": [
 "https://pic1.zhimg.com/v2-42b52e143568aa42ee3024a41fb6d5bc.jpg"
 ],
 "type": 0,
 "id": 9709446,
 "ga_prefix": "032709",
 "title": "传销和直销的区别是什么？「传销不用蹲监狱」是真的吗？"
 }
 ]
 }
 *
 */
public class ZhihuDailyLatestBean {
    private String date;
    private List<Stories> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    class Stories {
        public List<String> images;
        public int type;
        public int id;
        public String ga_prefix;
        public String title;
    }

}

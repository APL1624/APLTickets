package com.apl.ticket.been;

import java.util.List;

/**
 * Created by Administrator on 2017/4/3 0003.
 */

public class FoundReadTabBeen {


    /**
     * tags : [{"name":"新鲜报","id":"14"},{"name":"老时光","id":"13"},{"name":"定格秀","id":"9"},{"name":"说到位","id":"8"}]
     * retcode : 200
     * retdesc : 操作成功
     */

    private int retcode;
    private String retdesc;
    private List<Tab> tags;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public String getRetdesc() {
        return retdesc;
    }

    public void setRetdesc(String retdesc) {
        this.retdesc = retdesc;
    }

    public List<Tab> getTags() {
        return tags;
    }

    public void setTags(List<Tab> tags) {
        this.tags = tags;
    }

    public static class Tab {
        /**
         * name : 新鲜报
         * id : 14
         */

        private String name;
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}

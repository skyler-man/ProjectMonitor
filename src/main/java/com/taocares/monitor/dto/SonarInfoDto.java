package com.taocares.monitor.dto;

import lombok.ToString;

import java.util.List;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 17:34 2019/7/18
 */
@ToString
public class SonarInfoDto {


    /**
     * component : {"id":"AWvMpq-s8Bw_NvGJF_Op","key":"starter","name":"hxbase","qualifier":"TRK","measures":[{"metric":"complexity","value":"1084","periods":[{"index":1,"value":"1084"}]},{"metric":"violations","value":"1885","periods":[{"index":1,"value":"1636"}]},{"metric":"ncloc","value":"4375","periods":[{"index":1,"value":"4375"}]},{"metric":"bugs","value":"26","periods":[{"index":1,"value":"26"}]}]}
     * metrics : [{"key":"bugs","name":"Bugs","description":"Bugs","domain":"Reliability","type":"INT","higherValuesAreBetter":false,"qualitative":false,"hidden":false,"custom":false,"bestValue":"0"},{"key":"complexity","name":"Cyclomatic Complexity","description":"Cyclomatic complexity","domain":"Complexity","type":"INT","higherValuesAreBetter":false,"qualitative":false,"hidden":false,"custom":false},{"key":"ncloc","name":"Lines of Code","description":"Non commenting lines of code","domain":"Size","type":"INT","higherValuesAreBetter":false,"qualitative":false,"hidden":false,"custom":false},{"key":"violations","name":"Issues","description":"Issues","domain":"Issues","type":"INT","higherValuesAreBetter":false,"qualitative":true,"hidden":false,"custom":false,"bestValue":"0"}]
     * periods : [{"index":1,"mode":"previous_version","date":"2019-07-07T21:58:59+0800"}]
     */

    private ComponentBean component;
    private List<MetricsBean> metrics;
    private List<PeriodsBeanX> periods;

    public ComponentBean getComponent() {
        return component;
    }

    public void setComponent(ComponentBean component) {
        this.component = component;
    }

    public List<MetricsBean> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<MetricsBean> metrics) {
        this.metrics = metrics;
    }

    public List<PeriodsBeanX> getPeriods() {
        return periods;
    }

    public void setPeriods(List<PeriodsBeanX> periods) {
        this.periods = periods;
    }

    public static class ComponentBean {
        /**
         * id : AWvMpq-s8Bw_NvGJF_Op
         * key : starter
         * name : hxbase
         * qualifier : TRK
         * measures : [{"metric":"complexity","value":"1084","periods":[{"index":1,"value":"1084"}]},{"metric":"violations","value":"1885","periods":[{"index":1,"value":"1636"}]},{"metric":"ncloc","value":"4375","periods":[{"index":1,"value":"4375"}]},{"metric":"bugs","value":"26","periods":[{"index":1,"value":"26"}]}]
         */

        private String id;
        private String key;
        private String name;
        private String qualifier;
        private List<MeasuresBean> measures;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQualifier() {
            return qualifier;
        }

        public void setQualifier(String qualifier) {
            this.qualifier = qualifier;
        }

        public List<MeasuresBean> getMeasures() {
            return measures;
        }

        public void setMeasures(List<MeasuresBean> measures) {
            this.measures = measures;
        }

        public static class MeasuresBean {
            /**
             * metric : complexity
             * value : 1084
             * periods : [{"index":1,"value":"1084"}]
             */

            private String metric;
            private String value;
            private List<PeriodsBean> periods;

            public String getMetric() {
                return metric;
            }

            public void setMetric(String metric) {
                this.metric = metric;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public List<PeriodsBean> getPeriods() {
                return periods;
            }

            public void setPeriods(List<PeriodsBean> periods) {
                this.periods = periods;
            }

            public static class PeriodsBean {
                /**
                 * index : 1
                 * value : 1084
                 */

                private int index;
                private String value;

                public int getIndex() {
                    return index;
                }

                public void setIndex(int index) {
                    this.index = index;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }

    public static class MetricsBean {
        /**
         * key : bugs
         * name : Bugs
         * description : Bugs
         * domain : Reliability
         * type : INT
         * higherValuesAreBetter : false
         * qualitative : false
         * hidden : false
         * custom : false
         * bestValue : 0
         */

        private String key;
        private String name;
        private String description;
        private String domain;
        private String type;
        private boolean higherValuesAreBetter;
        private boolean qualitative;
        private boolean hidden;
        private boolean custom;
        private String bestValue;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isHigherValuesAreBetter() {
            return higherValuesAreBetter;
        }

        public void setHigherValuesAreBetter(boolean higherValuesAreBetter) {
            this.higherValuesAreBetter = higherValuesAreBetter;
        }

        public boolean isQualitative() {
            return qualitative;
        }

        public void setQualitative(boolean qualitative) {
            this.qualitative = qualitative;
        }

        public boolean isHidden() {
            return hidden;
        }

        public void setHidden(boolean hidden) {
            this.hidden = hidden;
        }

        public boolean isCustom() {
            return custom;
        }

        public void setCustom(boolean custom) {
            this.custom = custom;
        }

        public String getBestValue() {
            return bestValue;
        }

        public void setBestValue(String bestValue) {
            this.bestValue = bestValue;
        }
    }

    public static class PeriodsBeanX {
        /**
         * index : 1
         * mode : previous_version
         * date : 2019-07-07T21:58:59+0800
         */

        private int index;
        private String mode;
        private String date;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}

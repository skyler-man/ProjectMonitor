package com.taocares.monitor.dto;

import lombok.ToString;

import java.util.List;

/**
 * @Description: Sonar指标
 * @Author: LiuYiQiang
 * @Date: 17:34 2019/7/18
 */
@ToString
public class SonarInfoDto {


    /**
     * component : {"id":"AWvMpq-s8Bw_NvGJF_Op","key":"starter","name":"hxbase","qualifier":"TRK","measures":[{"metric":"complexity","value":"1084","periods":[{"index":1,"value":"1084"}]},{"metric":"bugs","value":"26","periods":[{"index":1,"value":"26"}]},{"metric":"vulnerabilities","value":"6","periods":[{"index":1,"value":"6"}]},{"metric":"code_smells","value":"1853","periods":[{"index":1,"value":"1604"}]},{"metric":"duplicated_lines_density","value":"41.3","periods":[{"index":1,"value":"0.29999999999999716"}]},{"metric":"coverage","value":"0.0","periods":[{"index":1,"value":"0.0"}]},{"metric":"violations","value":"1885","periods":[{"index":1,"value":"1636"}]},{"metric":"duplicated_lines","value":"2253","periods":[{"index":1,"value":"38"}]}]}
     */

    private ComponentBean component;

    public ComponentBean getComponent() {
        return component;
    }

    public void setComponent(ComponentBean component) {
        this.component = component;
    }

    public static class ComponentBean {
        /**
         * id : AWvMpq-s8Bw_NvGJF_Op
         * key : starter
         * name : hxbase
         * qualifier : TRK
         * measures : [{"metric":"complexity","value":"1084","periods":[{"index":1,"value":"1084"}]},{"metric":"bugs","value":"26","periods":[{"index":1,"value":"26"}]},{"metric":"vulnerabilities","value":"6","periods":[{"index":1,"value":"6"}]},{"metric":"code_smells","value":"1853","periods":[{"index":1,"value":"1604"}]},{"metric":"duplicated_lines_density","value":"41.3","periods":[{"index":1,"value":"0.29999999999999716"}]},{"metric":"coverage","value":"0.0","periods":[{"index":1,"value":"0.0"}]},{"metric":"violations","value":"1885","periods":[{"index":1,"value":"1636"}]},{"metric":"duplicated_lines","value":"2253","periods":[{"index":1,"value":"38"}]}]
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
}

package com.taocares.monitor.common;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.SearchRestClient;
import com.atlassian.jira.rest.client.domain.*;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;
import com.taocares.monitor.dto.JiraIssueInfo;
import lombok.extern.slf4j.Slf4j;
import mjson.Json;
import org.joda.time.DateTime;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 10:00 2019/7/9
 */
@Slf4j
public class JiraUtil {

    private static JiraRestClient restClient;

    private static final String USERNAME = "liuyiqiang";
    private static final String PASSWORD = "lyq@13561224858";

    static {
        try {
            final JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
            final URI jiraServerUri = new URI("http://yanfa.qdkaiya.com:8085");
            restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, USERNAME,
                    PASSWORD);
        } catch (Exception e) {
            log.error("登录失败",e);
        }
    }

    public static JiraRestClient getRestClient() {
        return restClient;
    }

    /**
     * @Description: 获取并返回指定的Issue对象
     * @Author: LiuYiQiang
     * @Date: 10:02 2019/7/9
     */
    public static Issue get_issue(String issueNum) throws URISyntaxException {
        try {
            final NullProgressMonitor pm = new NullProgressMonitor();
            final Issue issue = restClient.getIssueClient().getIssue(issueNum, pm);
            return issue;
        } catch (Exception e) {
            log.error("获取Issue对象失败",e);
        }
        return null;
    }

    /**
     * @Description: 获取指定JIRA备注部分的内容
     * @Author: LiuYiQiang
     * @Date: 10:02 2019/7/9
     */
    public static List<String> get_comments_body(Issue issue) throws URISyntaxException {
        try {
            List<String> comments = new ArrayList<String>();
            for (Comment comment : issue.getComments()) {
                comments.add(comment.getBody().toString());
            }
            return comments;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 获取指定JIRA的创建时间
     * @Author: LiuYiQiang
     * @Date: 10:02 2019/7/9
     */
    public static DateTime get_create_time(Issue issue) throws URISyntaxException {
        try {
            return issue.getCreationDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 获取指定JIRA的描述部分
     * @Author: LiuYiQiang
     * @Date: 10:03 2019/7/9
     */
    public static String get_description(Issue issue) throws URISyntaxException {
        try {
            return issue.getDescription();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 获取指定JIRA的标题
     * @Author: LiuYiQiang
     * @Date: 10:03 2019/7/9
     */
    public static String get_summary(Issue issue) throws URISyntaxException {
        try {
            return issue.getSummary();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 获取指定JIRA的报告人的名字
     * @Author: LiuYiQiang
     * @Date: 10:03 2019/7/9
     */
    public static String get_reporter(Issue issue) throws URISyntaxException {
        try {
            return issue.getReporter().getDisplayName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 获取指定JIRA的分派人的姓名列表
     * @Author: LiuYiQiang
     * @Date: 10:03 2019/7/9
     */
    public static ArrayList<String> get_assignees(Issue issue) throws URISyntaxException {
        try {
            Json json = Json.read(issue.getFieldByName("分派给").getValue().toString());
            Iterator<Json> assignees = json.asJsonList().iterator();
            ArrayList<String> assigneesNames = new ArrayList<String>();
            while (assignees.hasNext()) {
                Json assignee = Json.read(assignees.next().toString());
                String assigneeName = assignee.at("displayName").toString();
                assigneesNames.add(assigneeName);
            }
            return assigneesNames;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 获取指定JIRA的状态
     * @Author: LiuYiQiang
     * @Date: 10:03 2019/7/9
     */
    public static String get_status(Issue issue) throws URISyntaxException {
        try {
            return issue.getStatus().getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 获取指定JIRA的类型
     * @Author: LiuYiQiang
     * @Date: 10:03 2019/7/9
     */
    public static String get_issue_type(Issue issue) throws URISyntaxException {
        try {
            return issue.getIssueType().getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 获取指定JIRA的模块
     * @Author: LiuYiQiang
     * @Date: 10:03 2019/7/9
     */
    public static ArrayList<String> get_modules(Issue issue) throws URISyntaxException {
        try {
            ArrayList<String> arrayList = new ArrayList<String>();
            Iterator<BasicComponent> basicComponents = issue.getComponents().iterator();
            while (basicComponents.hasNext()) {
                String moduleName = basicComponents.next().getName();
                arrayList.add(moduleName);
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 获取指定JIRA的前端人员的姓名列表
     * @Author: LiuYiQiang
     * @Date: 10:04 2019/7/9
     */
    public static ArrayList<String> get_qianduans(Issue issue) throws URISyntaxException {
        try {
            ArrayList<String> qianduanList = new ArrayList<String>();
            Json json = Json.read(issue.getFieldByName("前端").getValue().toString());
            Iterator<Json> qianduans = json.asJsonList().iterator();
            while (qianduans.hasNext()) {
                Json qianduan = Json.read(qianduans.next().toString());
                String qianduanName = qianduan.at("displayName").toString();
                qianduanList.add(qianduanName);
            }
            return qianduanList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 获取指定JIRA的开发的姓名列表
     * @Author: LiuYiQiang
     * @Date: 10:04 2019/7/9
     */
    public static ArrayList<String> get_developers(Issue issue) throws URISyntaxException {
        try {
            ArrayList<String> developersList = new ArrayList<String>();
            Json json = Json.read(issue.getFieldByName("开发").getValue().toString());
            Iterator<Json> developers = json.asJsonList().iterator();
            while (developers.hasNext()) {
                Json developer = Json.read(developers.next().toString());
                String developerName = developer.at("displayName").toString();
                developersList.add(developerName);
            }
            return developersList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 获取指定JIRA的产品人员的姓名
     * @Author: LiuYiQiang
     * @Date: 10:04 2019/7/9
     */
    public static String get_product(Issue issue) throws URISyntaxException {
        try {
            String product_field = issue.getFieldByName("产品人员").getValue().toString();
            return Json.read(product_field).at("displayName").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



//    /**
//     * @Description: 得到单一项目信息
//     * @Author: LiuYiQiang
//     * @Date: 11:13 2019/7/9
//     */
//    public static String getProjectByHttp(Project project,String key) throws InterruptedException, ExecutionException {
//        String url = project.getSelf().toString();
//        url = url.substring(0,url.lastIndexOf("/")+1);
//        return HttpUtil.getHttp(url + key);
//    }


    /**
     * @Description: 得到单一项目信息
     * @Author: LiuYiQiang
     * @Date: 11:13 2019/7/9
     */
    public static Project getProject(String porjectKEY) throws InterruptedException, ExecutionException {
        try {
            NullProgressMonitor pm = new NullProgressMonitor();
            Project project = restClient.getProjectClient()
                    .getProject(porjectKEY,pm);
            return project;
        } finally {
        }
    }

    /**
     * @Description: 得到单一问题信息
     * @Author: LiuYiQiang
     * @Date: 11:13 2019/7/9
     */
    public static Issue getIssue(String issueKEY) throws InterruptedException, ExecutionException {
        try {
            NullProgressMonitor pm = new NullProgressMonitor();
            Issue issue = restClient.getIssueClient()
                    .getIssue(issueKEY,pm);
            return issue;

        } finally {
        }
    }

    /**
     * @Description: 通过KEY获取问题
     * @Author: LiuYiQiang
     * @Date: 11:13 2019/7/9
     */
    public static Issue findIssueByIssueKey(String issueKey) {
        SearchRestClient searchClient = restClient.getSearchClient();
        String jql = "issuekey = \"" + issueKey + "\"";
        NullProgressMonitor pm = new NullProgressMonitor();
        SearchResult results = searchClient.searchJql(jql,pm);
        return (Issue) results.getIssues().iterator().next();
    }

    /**
     * @Description: 获取问题的所有字段
     * @Author: LiuYiQiang
     * @Date: 8:35 2019/7/10
     */
    public static void getIssueFields(String issueKEY) throws InterruptedException, ExecutionException {
        try {
            NullProgressMonitor pm = new NullProgressMonitor();
            Issue issue = restClient.getIssueClient()
                    .getIssue(issueKEY,pm);
            Iterable<Field> fields = issue.getFields();
            Iterator<Field> it = fields.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }

        } finally {
        }
    }


    /**
     * @Description: 得到所有项目信息
     * @Author: LiuYiQiang
     * @Date: 8:35 2019/7/10
     */
    public static ArrayList<String> getAllProjects(){
        final NullProgressMonitor pm = new NullProgressMonitor();
        Iterable<BasicProject> allProjects = restClient
                .getProjectClient().getAllProjects(pm);
        Iterator<BasicProject> iterator = allProjects.iterator();
        ArrayList<String> issueKeys = new ArrayList<>();
        //获取项目所有key
        while (iterator.hasNext()){
            String key = iterator.next().getKey();
            issueKeys.add(key);
        }
        return issueKeys;
    }

    /**
     * @Description: 通过jql语句进行查询并返回一个包含issue的key的数组
     * @Author: LiuYiQiang
     * @Date: 8:35 2019/7/10
     */
    public static JiraIssueInfo search_jql(String jql, String projectKey) throws ExecutionException, InterruptedException {
        JiraIssueInfo jiraIssueInfo = new JiraIssueInfo();
        int bugs = 0;
        Map<String,Integer> jiraBugInfo = new HashMap<>();
        final NullProgressMonitor pm = new NullProgressMonitor();
        Long startTime = System.currentTimeMillis();
        SearchResult searchResult = restClient.getSearchClient().searchJql(jql, pm);
        Long endTime = System.currentTimeMillis();
        log.info("查询" + projectKey + "总耗时：{}", endTime - startTime);
        Iterator<BasicIssue> basicIssues = searchResult.getIssues().iterator();
        while (basicIssues.hasNext()) {
            BasicIssue basicIssue = basicIssues.next();
            String key = basicIssue.getKey();
            Issue issue = getIssue(key);
            if(issue.getIssueType() != null && issue.getIssueType().getName() != null && issue.getIssueType().getName().equals("故障")){
                bugs++;
            }
            if(issue.getStatus().getName() == null){
                continue;
            }
            if(jiraBugInfo.get(issue.getStatus().getName()) == null){
                jiraBugInfo.put(issue.getStatus().getName(),1);
            }else {
                jiraBugInfo.put(issue.getStatus().getName(),jiraBugInfo.get(issue.getStatus().getName())+1);
            }
        }
        jiraIssueInfo.setJiraBugInfo(jiraBugInfo);
        jiraIssueInfo.setBugs(bugs);
        return jiraIssueInfo;
    }

}

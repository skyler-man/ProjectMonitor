package com.taocares.monitor.service;

import com.taocares.monitor.dto.JenkinsJobNameDto;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface IJenkinsService {
    void getJenkinsInfoForSchedule() throws URISyntaxException, IOException;

    List<JenkinsJobNameDto> getJenkinsJobNames();
}

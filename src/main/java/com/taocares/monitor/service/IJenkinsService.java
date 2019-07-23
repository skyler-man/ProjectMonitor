package com.taocares.monitor.service;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IJenkinsService {
    void getJenkinsInfoForSchedule() throws URISyntaxException, IOException;
}

/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zuoxiaolong.niubi.job.service.impl;

import com.zuoxiaolong.niubi.job.persistent.entity.MasterSlaveJob;
import com.zuoxiaolong.niubi.job.service.MasterSlaveJobService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Xiaolong Zuo
 * @since 0.9.4.2
 */
public class MasterSlaveJobServiceImplTest extends AbstractSpringContextTest{

    @Autowired
    protected MasterSlaveJobService masterSlaveJobService;

    @Test
    public void getAllJobs() {
        List<MasterSlaveJob> jobs = masterSlaveJobService.getAllJobs();
        Assert.assertNotNull(jobs);
        Assert.assertTrue(jobs.size() == 0);
        masterSlaveJobService.saveJob(ClassLoader.getSystemClassLoader().getResource("niubi-job-example-spring.jar").getFile(), "com.zuoxiaolong");
        jobs = masterSlaveJobService.getAllJobs();
        Assert.assertNotNull(jobs);
        Assert.assertTrue(jobs.size() == 2);
    }

    @Test
    public void getJob() {
        masterSlaveJobService.saveJob(ClassLoader.getSystemClassLoader().getResource("niubi-job-example-spring.jar").getFile(), "com.zuoxiaolong");
        MasterSlaveJob job = masterSlaveJobService.getJob("com.zuoxiaolong.niubi.job.example.spring.job.Job1", "test", "niubi-job-example-spring.jar");
        Assert.assertNotNull(job);
    }

    @Test
    public void getJarFileNameList() {
        masterSlaveJobService.saveJob(ClassLoader.getSystemClassLoader().getResource("niubi-job-example-spring.jar").getFile(), "com.zuoxiaolong");
        List<String> jarFileNameList = masterSlaveJobService.getJarFileNameList("com.zuoxiaolong.niubi.job.example.spring.job.Job1", "test");
        Assert.assertNotNull(jarFileNameList);
        Assert.assertTrue(jarFileNameList.size() == 1);
        Assert.assertEquals("niubi-job-example-spring.jar" , jarFileNameList.get(0));
    }

}

/*************************GO-LICENSE-START*********************************
 * Copyright 2014 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *************************GO-LICENSE-END***********************************/

package com.thoughtworks.go.server.service.dd;

import com.thoughtworks.go.helper.MaterialConfigsMother;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;

public class DependencyFanInNodeTest {
    @Test
    public void shouldNotAllowScmMaterialAsDependencyFaninNode() throws Exception {
        try {
            new DependencyFanInNode(MaterialConfigsMother.svnMaterialConfig());
            fail("should not allow pipeline as root node");
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Not a valid root node material type"));
        }
    }

    @Test
    public void shouldNotAllowPackageMaterialAsDependencyFaninNode() throws Exception {
        try {
            new DependencyFanInNode(MaterialConfigsMother.packageMaterialConfig());
            fail("should not allow pipeline as root node");
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Not a valid root node material type"));
        }
    }

    @Test
    public void shouldAllowDependencyMaterialAsDependencyFaninNode() throws Exception {
        assertThat(new DependencyFanInNode(MaterialConfigsMother.dependencyMaterialConfig()), is(not(nullValue())));
    }

}

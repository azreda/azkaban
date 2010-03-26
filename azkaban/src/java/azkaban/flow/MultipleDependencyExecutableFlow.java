/*
 * Copyright 2010 LinkedIn, Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package azkaban.flow;

import org.joda.time.DateTime;

import java.util.List;

/**
 *
 */
public class MultipleDependencyExecutableFlow implements ExecutableFlow
{
    private final GroupedExecutableFlow dependeesGrouping;
    private final ComposedExecutableFlow actualFlow;
    private final String id;
    private final ExecutableFlow depender;

    public MultipleDependencyExecutableFlow(String id, ExecutableFlow depender, ExecutableFlow... dependees)
    {
        this.id = id;
        this.depender = depender;
        dependeesGrouping = new GroupedExecutableFlow(id, dependees);
        actualFlow = new ComposedExecutableFlow(id, this.depender, dependeesGrouping);
    }

    @Override
    public String getId()
    {
        return id;
    }

    @Override
    public String getName()
    {
        return actualFlow.getName();
    }

    @Override
    public void execute(FlowCallback callback)
    {
        actualFlow.execute(callback);
    }

    @Override
    public boolean cancel()
    {
        return actualFlow.cancel();
    }

    @Override
    public Status getStatus()
    {
        return actualFlow.getStatus();
    }

    @Override
    public boolean reset()
    {
        return actualFlow.reset();
    }

    @Override
    public boolean markCompleted()
    {
        return actualFlow.markCompleted();
    }

    @Override
    public boolean hasChildren()
    {
        return true;
    }

    @Override
    public List<ExecutableFlow> getChildren()
    {
        return dependeesGrouping.getChildren();
    }

    @Override
    public DateTime getStartTime()
    {
        return actualFlow.getStartTime();
    }

    @Override
    public DateTime getEndTime()
    {
        return actualFlow.getEndTime();
    }

    @Override
    public String toString()
    {
        return "MultipleDependencyExecutableFlow{" +
               "dependeesGrouping=" + dependeesGrouping +
               ", actualFlow=" + actualFlow +
               '}';
    }

    public ComposedExecutableFlow getActualFlow()
    {
        return actualFlow;
    }
}
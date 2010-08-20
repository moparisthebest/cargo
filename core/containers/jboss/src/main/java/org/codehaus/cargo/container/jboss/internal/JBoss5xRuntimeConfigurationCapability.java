/*
 * ========================================================================
 *
 * Codehaus CARGO, copyright 2004-2010 Vincent Massol.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package org.codehaus.cargo.container.jboss.internal;

import java.util.Map;

import org.codehaus.cargo.container.property.GeneralPropertySet;

/**
 * Capabilities of JBoss's {@link org.codehaus.cargo.container.jboss.JBossRuntimeConfiguration}
 * configuration.
 *  
 * @version $Id$
 */
public class JBoss5xRuntimeConfigurationCapability extends
    AbstractJBossRuntimeConfigurationCapability
{
    /**
     * Initialize the configuration-specific supports Map.
     */
    public JBoss5xRuntimeConfigurationCapability()
    {
        super();

        this.supportsMap.put(GeneralPropertySet.RMI_PORT, Boolean.TRUE);
    }

    /**
     * {@inheritDoc}
     * @see org.codehaus.cargo.container.spi.configuration.AbstractStandaloneLocalConfigurationCapability#getPropertySupportMap()
     */
    @Override
    protected Map getPropertySupportMap()
    {
        return this.supportsMap;
    }
}
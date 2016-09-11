/*
 * ========================================================================
 *
 * Codehaus CARGO, copyright 2004-2011 Vincent Massol, 2012-2016 Ali Tokmen.
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
package org.codehaus.cargo.container.tomee;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.cargo.container.InstalledLocalContainer;
import org.codehaus.cargo.container.LocalContainer;
import org.codehaus.cargo.container.configuration.ConfigurationCapability;
import org.codehaus.cargo.container.tomcat.Tomcat8xStandaloneLocalConfiguration;
import org.codehaus.cargo.container.tomcat.TomcatCopyingInstalledLocalDeployer;
import org.codehaus.cargo.container.tomee.internal.TomeeStandaloneLocalConfigurationCapability;

/**
 * Standalone local configuration for TomEE 7.x.
 */
public class Tomee7xStandaloneLocalConfiguration extends Tomcat8xStandaloneLocalConfiguration
{

    /**
     * {@inheritDoc}
     * 
     * @see TomcatStandaloneLocalConfigurationCapability
     */
    private static ConfigurationCapability capability =
        new TomeeStandaloneLocalConfigurationCapability();

    /**
     * {@inheritDoc}
     * 
     * @see Tomcat8xStandaloneLocalConfiguration#Tomcat8xStandaloneLocalConfiguration(String)
     */
    public Tomee7xStandaloneLocalConfiguration(String dir)
    {
        super(dir);

        setProperty(TomeePropertySet.APPS_DIRECTORY, "apps");
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.codehaus.cargo.container.tomcat.Tomcat8xStandaloneLocalConfiguration#getCapability()
     */
    @Override
    public ConfigurationCapability getCapability()
    {
        return capability;
    }

    /**
     * {@inheritDoc}
     * 
     * @see Object#toString()
     */
    @Override
    public String toString()
    {
        return "TomEE 7.x Standalone Configuration";
    }

    /**
     * {@inheritDoc}
     * @see org.codehaus.cargo.container.spi.configuration.AbstractLocalConfiguration#configure(LocalContainer)
     */
    @Override
    protected void doConfigure(LocalContainer container) throws Exception
    {
        super.doConfigure(container);

        String apps = getPropertyValue(TomeePropertySet.APPS_DIRECTORY);
        String tomeeXml = getFileHandler().append(getHome(), "conf/tomee.xml");
        Map<String, String> replacements = new HashMap<String, String>(1);
        replacements.put(
            "<!-- activate next line to be able to deploy applications in apps -->",
            "<!-- activate deployment applications in " +  apps + " directory -->");
        replacements.put(
            "<!-- <Deployments dir=\"apps\" /> -->",
            "<Deployments dir=\"" +  apps + "\" />");
        getFileHandler().replaceInFile(tomeeXml, replacements, "UTF-8");
    }

    /**
     * {@inheritDoc} TomEE provides its own transaction factory with openejb, so we don't add
     * <code>org.objectweb.jotm.UserTransactionFactory</code> unlike Tomcat
     */
    @Override
    protected void setupTransactionManager()
    {
        // Nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TomcatCopyingInstalledLocalDeployer createDeployer(LocalContainer container)
    {
        return new TomeeCopyingInstalledLocalDeployer((InstalledLocalContainer) container);
    }

}
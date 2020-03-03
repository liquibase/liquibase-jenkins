package org.liquibase.jenkins;

import hudson.Extension;
import hudson.util.FormValidation;
import jenkins.model.GlobalConfiguration;
import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;

/**
 * Example of Jenkins global configuration.
 */
@Extension
public class LiquibaseConfiguration extends GlobalConfiguration {

    /** @return the singleton instance */
    public static LiquibaseConfiguration get() {
        return GlobalConfiguration.all().get(LiquibaseConfiguration.class);
    }

    private String label;
    private String liquibaseCmd;
    private String liquibaseDrivers;

	public LiquibaseConfiguration() {
        // When Jenkins is restarted, load any saved configuration from disk.
        load();
    }

	public FormValidation doCheckLabel(@QueryParameter String value) {
        if (StringUtils.isEmpty(value)) {
            return FormValidation.warning("Please specify a label.");
        }
        return FormValidation.ok();
    }

	/** @return the currently configured label, if any */
    public String getLabel() {
        return label;
    }

    /** @return the currently configured Liquibase Command, if any */
    public String getLiquibaseCmd() {
		return liquibaseCmd;
	}

	/** @return the currently configured Liquibase Command, if any */
    public String getLiquibaseDrivers() {
		return liquibaseDrivers;
	}

    /**
     * Together with {@link #getLabel}, binds to entry in {@code config.jelly}.
     * @param label the new value of this field
     */
    @DataBoundSetter
    public void setLabel(String label) {
        this.label = label;
        save();
    }

    /**
     * Together with {@link #getLiquibaseCmd}, binds to entry in {@code config.jelly}.
     * @param liquibaseCmd the location of the Liquibase command
     */
    @DataBoundSetter
    public void setLiquibaseCmd(String liquibaseCmd) {
		this.liquibaseCmd = liquibaseCmd;
		save();
	}

    /**
     * Together with {@link #getLiquibaseDrivers}, binds to entry in {@code config.jelly}.
     * @param liquibaseDrivers the location of the Liquibase JDBC drivers
     */
    @DataBoundSetter
    public void setLiquibaseDrivers(String liquibaseDrivers) {
		this.liquibaseDrivers = liquibaseDrivers;
		save();
	}

}

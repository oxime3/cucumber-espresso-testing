package cucumber.api.android;

import android.os.Bundle;
import androidx.test.runner.AndroidJUnitRunner;
import cucumber.runtime.android.CucumberJUnitRunnerBuilder;

/**
 * {@link AndroidJUnitRunner} for cucumber tests. It supports running tests from Android Tests Orchestrator
 */
public class CucumberAndroidJUnitRunner extends AndroidJUnitRunner {

    public static final String CUCUMBER_ANDROID_TEST_CLASS = "cucumberAndroidTestClass";
    private static final String ARGUMENT_ORCHESTRATOR_RUNNER_BUILDER = "runnerBuilder";
    private static final String ARGUMENT_ORCHESTRATOR_CLASS = "class";
    private Bundle arguments;

    @Override
    public void onCreate(final Bundle bundle) {
        bundle.putString(ARGUMENT_ORCHESTRATOR_RUNNER_BUILDER, CucumberJUnitRunnerBuilder.class.getName());

        String testClass = bundle.getString(ARGUMENT_ORCHESTRATOR_CLASS);
        if (testClass != null && !testClass.isEmpty()){
            //if this runner is executed for single class (e.g. from orchestrator or spoon), we set
            //special option to let CucumberJUnitRunner handle this
             bundle.putString(CUCUMBER_ANDROID_TEST_CLASS, testClass);
        }
        //there is no need to scan all classes - we can fake this execution to be for single class
        //because we delegate test execution to CucumberJUnitRunner
        bundle.putString(ARGUMENT_ORCHESTRATOR_CLASS, CucumberJUnitRunnerBuilder.class.getName());
        
        arguments = bundle;

        super.onCreate(bundle);
    }

    public Bundle getArguments() {
        return arguments;
    }
}

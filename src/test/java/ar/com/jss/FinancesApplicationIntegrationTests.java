package ar.com.jss;

import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.*;

@RunWith(AnnotatedEmbedderRunner.class)
@Configure(
        storyControls        = FinancesApplicationIntegrationTests.FinancesStoryControl.class,
        storyLoader          = FinancesApplicationIntegrationTests.FinancesStoryLoader.class,
        storyReporterBuilder = FinancesApplicationIntegrationTests.FinancesStoryReportBuilder.class
)
@UsingEmbedder(embedder = Embedder.class)
@UsingSteps(packages = {"ar.com.jss.steps"})
public class FinancesApplicationIntegrationTests extends InjectableEmbedder {

    @Test
    public void run() {
        FinancesApplication.main(new String[]{""});
        List<String> storyPaths = new StoryFinder().findPaths(codeLocationFromClass(this.getClass()), "**/*.story", "");
        injectedEmbedder().runStoriesAsPaths(storyPaths);
    }

    public static class FinancesStoryControl extends StoryControls {
        public FinancesStoryControl() {
            doDryRun(false);
            doSkipScenariosAfterFailure(false);
        }
    }

    public static class FinancesStoryLoader extends LoadFromClasspath {
        public FinancesStoryLoader() {
            super(AnnotatedEmbedderRunner.class.getClassLoader());
        }
    }

    public static class FinancesStoryReportBuilder extends StoryReporterBuilder {
        public FinancesStoryReportBuilder() {
            this.withFormats(CONSOLE, TXT, HTML, XML).withDefaultFormats();
        }
    }
}

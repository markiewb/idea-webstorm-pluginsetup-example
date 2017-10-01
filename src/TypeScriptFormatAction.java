import com.intellij.lang.javascript.linter.tslint.fix.TsLintFileFixAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;

public class TypeScriptFormatAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        MyTsLintFileFixAction action = new MyTsLintFileFixAction();
        Runnable callback = new Runnable() {
            @Override
            public void run() {
                VirtualFile file = e.getData(CommonDataKeys.VIRTUAL_FILE);
                if (file != null) {
                    file.refresh(false, false);

                    System.out.println(String.format("Refreshing %s after tslint-fixing", file));
                }
            }
        };

        Task task = action.createTask(e.getProject(), Arrays.asList(e.getData(CommonDataKeys.VIRTUAL_FILE)), callback);
        ProgressManager.getInstance().run(task);
    }

    private static class MyTsLintFileFixAction extends TsLintFileFixAction {

        @Override
        protected Task createTask(@NotNull Project project, @NotNull Collection<VirtualFile> filesToProcess, @NotNull Runnable completeCallback) {
            // HACK: Override non-final Action-class to get access to the hidden createTask-method
            return super.createTask(project, filesToProcess, completeCallback);
        }
    }
}

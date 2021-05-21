import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.Objects;
import java.util.Optional;

public class FileSelectAction extends AnAction {

    @Override
    public void update(AnActionEvent event) {
        VirtualFile vf = event.getData(CommonDataKeys.VIRTUAL_FILE);
        final var targetExtension = "xml";
        boolean active = Objects.nonNull(vf) && targetExtension.equals(vf.getExtension());
        event.getPresentation().setEnabledAndVisible(active);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project currentProject = e.getProject();
        VirtualFile vf = e.getData(CommonDataKeys.VIRTUAL_FILE);
        String fullPath = Optional.ofNullable(vf)
                .map(VirtualFile::getCanonicalPath)
                .orElse(null);
        String basePath = Optional.ofNullable(currentProject)
                .map(Project::getBasePath)
                .orElse("");
        String filePath = Optional.ofNullable(fullPath)
                .map(v -> v.substring(basePath.length()))
                .orElse("");

        int rtn = Messages.showOkCancelDialog(currentProject,
                String.format("action for %s", filePath),
                "action for file", "Ok", "Cancel", Messages.getQuestionIcon());

        if (rtn == Messages.OK) {
            // TODO: action for file.

            // notification of finished action
            final Notification notification = new Notification(
                    "FileSelectActionNotification",
                    "action for file",
                    String.format("finished to action for %s", filePath),
                    NotificationType.INFORMATION);
            notification.notify(currentProject);
        }
    }
}

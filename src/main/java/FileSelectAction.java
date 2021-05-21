import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.Objects;

public class FileSelectAction extends AnAction {

    @Override
    public void update(AnActionEvent event) {
        VirtualFile vf = event.getData(CommonDataKeys.VIRTUAL_FILE);
        // ここでは、拡張子「hoge」の場合のみメニューを表示している
        final var targetExtension = "hoge";
        boolean active = Objects.nonNull(vf) && targetExtension.equals(vf.getExtension());
        event.getPresentation().setEnabledAndVisible(active);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
    }
}

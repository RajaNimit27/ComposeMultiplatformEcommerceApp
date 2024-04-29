import androidx.compose.ui.window.ComposeUIViewController
import com.example.project.db.Database
import com.example.project.db.DatabaseDriverFactory
import com.example.project.db.DatabaseProvider

fun MainViewController() = ComposeUIViewController {
    DatabaseProvider.setDriveFactory(DatabaseDriverFactory());
    App()
}

import androidx.compose.ui.window.ComposeUIViewController
import com.example.project.db.Database
import com.example.project.db.DatabaseDriverFactory
import com.example.project.db.DataBaseProviderObject

fun MainViewController() = ComposeUIViewController {
    DataBaseProviderObject.setDriveFactory(DatabaseDriverFactory());
    App()
}

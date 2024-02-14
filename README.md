實作影片 [How to Make a Clean Architecture Note App](https://www.youtube.com/watch?v=8YPXv7xKh2w&ab_channel=PhilippLackner)

Github：[PhilippLackner-CleanArchitectureNoteApp](https://github.com/RainBowT0506/PhilippLackner-CleanArchitectureNoteApp)

# Introduction
## 影片內容概述：
- 影片介紹乾淨架構（Clean Architecture）的教學，是之前加密貨幣應用教學的延續。
- 觀眾的觀看與回饋對於未來影片的製作方向至關重要，教學將從頭開始解釋乾淨架構的概念。
- 教學涵蓋了MVVM、依賴注入、Room 資料庫等技術，以及如何在 Android 專案中應用乾淨架構。

## 教學內容概述：
- 影片將介紹一個 Node App，使用 Jetpack Compose 構建其使用者界面。
- 功能包括添加、編輯、刪除節點，以及按照不同標準對節點進行排序。

## 學習價值與應用：
- 影片內容對於 Android 開發者的技能提升和作品集建立具有重要價值。
- 乾淨架構是業界廣泛採用的標準，對於各種 Android 專案都具有重要意義。

# What is clean architecture?
## 概念解釋
- 乾淨架構（Clean Architecture）是由知名程式設計師 Uncle Bob 提出的一種架構方法。
- 相對於 MVVM 等現有架構，乾淨架構是一種擴展，採用了 SOLID 原則，並引入了新的概念如使用案例（use cases）。
- 乾淨架構的主要目的在於使應用程式具有可擴展性，尤其對於大型應用程式更為重要。
## 架構設計原則
- 良好的架構應該具備多個方面的特性，包括易擴展性、易測試性、易理解性以及可替換性。
- 可擴展性指能夠輕鬆地添加新功能到架構中。
- 易測試性指能夠輕鬆地編寫並執行測試案例。
- 易理解性指新成員能夠快速理解專案的架構與功能。
- 可替換性指能夠輕鬆地替換架構中的元件，例如更換 HTTP 函式庫。
## 實踐方式
- 在實際應用中，大型應用程式通常會使用多個模組（modules）進行開發，但這樣的架構複雜度不適合單一影片教學。
- 影片將專注於模擬真實應用程式中的模組結構，並在單一模組內進行演示。
- 觀眾可以透過影片瞭解到乾淨架構的理論概念，若有興趣可以訂閱免費的電子報以獲得更多相關建議與資訊。 

# Setting up the project structure
![image](https://github.com/RainBowT0506/PhilippLackner-CleanArchitectureNoteApp/assets/109667537/0cb1596f-7f7d-49c3-aef8-1e87e402cdf7)

## 概念解釋
- 在開始之前，需要先下載初始專案，該專案包含了所有預設的樣式、依賴與專案結構。
- 專案結構採用了乾淨架構，將應用程式分為不同的層級（Presentation、Data、Domain），以確保程式碼的可擴展性與可維護性。

## 專案結構設計
- 將專案結構分為功能（Feature）層和通用（Core）層，功能層內包含單一功能的所有相關元件，通用層則包含整個應用程式的共用元件。
- 功能層內部再細分為 Presentation、Data、Domain 等不同層級，以更清晰地組織程式碼。

## 開發流程
- 依據功能和層級，建立對應的封包結構，例如 Presentation 層內有單獨的封包用於儲存各個畫面的相關元件。
- 通過 Dependency Injection 來管理元件之間的依賴關係，使得程式碼更加模組化且易於維護。

# Setting up the SQL Database
## 概念解釋
- 在開始實作之前，首先要建立 Room 資料庫，作為我們的資料來源。
- 在 Room 資料庫中，需要建立資料表和資料存取對象（DAO）。

## Node 類別的建立
- 在 `model` 封包中建立 `Node` 類別，這是一個資料實體（Entity）。
- `Node` 類別包含標題、內容、時間戳記、顏色和主鍵等屬性，並標註為 Room 的資料表。

## DAO 的建立
- 在 `data source` 封包中建立 `NodeDao` 介面，用於定義與資料庫的交互操作，包括插入、刪除和查詢等功能。
- 使用 `@Query` 標註定義 SQL 查詢，以執行資料庫操作。
- 使用 `suspend` 關鍵字標註異步操作的函式。

## 資料庫類別的建立
- 在 `data source` 封包中建立 `NodeDatabase` 類別，繼承自 `RoomDatabase`。
- `NodeDatabase` 類別標註為 `@Database`，定義資料庫版本和包含的資料表。
- 定義了一個抽象的 `NodeDao` 成員，Room 將會自動生成實作。

# NoteRepository
## Repository 的功能與作用
- Repository 的作用是直接訪問資料來源，可能是資料庫或 API，並將資料傳遞給對應的使用案例。
- Repository 負責決定從哪個資料來源獲取資料，例如從快取或 API。
- 使用案例不需要知道資料來源，它們只需要獲取資料並進行相應的處理。

## Repository 介面的定義
- 在領域層中的 `repository` 封包建立 `NodeRepository` 介面，用於定義對資料庫操作的抽象方法。
- 使用介面有助於測試，因為可以輕鬆地創建假版本的 Repository 用於測試案例。

## Repository 實作的建立
- 在 `repository` 封包建立 `NodeRepositoryImplementation` 類別，實作 `NodeRepository` 介面。
- 實作中包含了對資料庫操作的具體邏輯，例如獲取、插入和刪除節點。
- 在這個簡單的應用中，Repository 只是將資料庫操作委託給 DAO，但在複雜的應用中可能需要包含更多的邏輯處理。



# GetNotes use case
## Use Case 的功能與作用
- Use Case 包含應用程式的業務邏輯，取代了以前在 MVVM 架構中由 View Model 處理的工作。
- Use Case 是指一個應用程式執行的單一任務，例如獲取節點、新增節點或刪除節點等。
- 使用 Use Case 可以使程式碼更易讀，因為每個 Use Case 只處理單一任務，並且命名清晰明確。

## Use Case 的設計原則與優點
- Use Case 的設計原則是每個 Use Case 只應該有一個公開的函式，可以被外部調用，這使得程式碼更加模組化和清晰。
- 使用 Use Case 可以提高程式碼的重用性，因為它們可以在不同的 View Model 中重複使用，避免了程式碼的重複性。

## Use Case 的實作步驟與運作方式
1. 建立 Use Case 類別：在 `use cases` 封包中建立相應的 Use Case 類別，例如 `GetNotesUseCase`。
2. 實作 Use Case 類別：實作類別中的 `invoke` 函式，並在其中編寫業務邏輯。
3. 使用 Repository：Use Case 通常會呼叫 Repository 來執行資料庫操作，以獲取所需的資料。
4. 使用業務邏輯：View Model 或其他部分可以呼叫 Use Case 類別來執行相應的業務邏輯。



# DeleteNote use case
## DeleteNote Use Case 的建立
- 在 Use Case 封包中創建一個名為 `DeleteNote` 的 Kotlin 類別，負責處理刪除節點的相關業務邏輯。
- DeleteNote Use Case 的作用是與資料庫進行互動，呼叫 Repository 的 `deleteNode` 函式，實現節點的刪除。

## DeleteNote Use Case 實作細節
- DeleteNote Use Case 採用 `suspend operator function invoke`，表示可以以挂起的方式執行，使得操作不會阻塞主執行緒。
- 透過構造函式傳入 Node Repository 的實例，確保 Use Case 可以與 Repository 進行交互。
- 在 `invoke` 函式中，呼叫 Repository 的 `deleteNode` 函式，傳入要刪除的節點。

# NoteUseCases wrapper class
## Node Use Cases 封裝類別的建立
- 在 Use Cases 封包中建立一個名為 `NodeUseCases` 的 Kotlin 類別，此類別將包含所有與節點功能相關的 Use Case。
- NodeUseCases 類別的目的是將所有節點相關的 Use Case 包裝成一個類別，以便將其注入到 ViewModel 中。

## Node Use Cases 封裝類別的實作細節
- NodeUseCases 是一個資料類別（data class），用來集中管理節點功能相關的所有 Use Cases。
- 在 NodeUseCases 類別中，定義了變數來存儲每個單獨的 Use Case，包括 `getNodesUseCase` 和 `deleteNodeUseCase`。
- 透過將所有 Use Cases 封裝在一個類別中，可以使 ViewModel 的構造函式更加清晰簡潔，同時提高了程式碼的可讀性和維護性。 

 
# Dagger-Hilt Setup
## Dagger-Hilt 設置流程概述
- Dagger-Hilt 是一個用於 Android 應用程式的依賴注入框架，可簡化依賴注入的設置流程。
- 在設置 Dagger-Hilt 時，需要建立應用程式類別、註冊應用程式類別於清單檔中、以及提供依賴關係的模組。

## 應用程式類別設置
- 在根封裝中建立一個名為 `NodeApp` 的應用程式類別，並使用 `@HiltAndroidApp` 注解。
- 在清單檔中註冊應用程式類別，以便 Dagger-Hilt 正確識別並初始化應用程式。

## 依賴注入模組設置
- 在 DI 封裝中建立一個名為 `AppModule` 的 Kotlin 物件，用於提供應用程式所需的依賴關係。
- 在 AppModule 中，使用 `@Module` 和 `@InstallIn` 注解將依賴注入到單例組件中，確保依賴關係在整個應用程式生命週期中保持一致。
- 在 AppModule 中，提供了 Room 數據庫的實例、Node Repository 的實例以及 Node Use Cases 的實例，以滿足應用程式的依賴需求。
 
# NotesViewModel
## ViewModel 負責管理 UI 狀態：
- ViewModel 是根據 Clean Architecture 的設計原則，將 UI 與業務邏輯分離，負責管理 UI 狀態和處理用戶操作事件。
- 這個 ViewModel 主要處理用戶界面上的事件，並使用用例（Use Cases）來處理業務邏輯。

## 建立 ViewModel 和狀態物件：
- 創建了名為 `NodesViewModel` 的 ViewModel 類別，並在建構函式中注入了用例的包裝類別。
- 定義了一個狀態類別 `NodesState`，用於保存 UI 的當前狀態，包括節點列表、節點排序方式和排序區段的可見性。

## 管理 UI 事件：
- 使用 `sealed class` 定義了 `NodesEvent`，表示不同的 UI 事件，例如訂單更改、節點刪除、節點還原和排序區段切換。
- 在 ViewModel 中實現了 `onEvent` 函式，用於接收來自 UI 的事件並根據事件類型執行相應的操作。

## 處理節點操作：
- 實現了刪除節點和還原節點的邏輯，並在 ViewModel 中處理了這些操作。
- 將新增節點的邏輯封裝在了一個獨立的用例中，並在需要時從 ViewModel 調用。

## 維護 UI 狀態：
- 使用狀態物件來保存 UI 的當前狀態，並在接收到事件後更新狀態。
- 使用 Kotlin 的 `copy` 函式來創建狀態的新實例，以便在更新時保持不可變性。

## 協調與取消異步任務：
- 在處理異步任務時，使用了 Kotlin 的協程來管理執行緒。
- 使用 `Job` 物件來追蹤異步任務，並在需要時取消舊的任務以避免競態條件和資源浪費。
 
 
# NotesScreen
## DefaultRadioButton Composable
- 這個 composable 主要是用來建立一個帶有文字和單選按鈕的 UI 元件。
- 使用了 `Row` 來包裹單選按鈕和文字，並使用了 `Spacer` 來創建間距。
- 支援參數包括文字、是否選中、選中時的回調函數以及修飾符。

## OrderSection Composable
- 這個 composable 主要是用來建立一個包含多個單選按鈕的排序區域。
- 使用了 `Column` 來將單選按鈕分成兩個橫列，並在選中時觸發回調函數。
- 支援參數包括修飾符和節點排序，以及排序更改時的回調函數。

## NodeItem Composable
- 這個 composable 主要是用來顯示一個節點的內容，包括標題、內容和刪除按鈕。
- 使用了 `Canvas` 來實現圓角矩形以及切除部分角落的效果，並包含刪除按鈕的功能。
- 支援參數包括節點數據、修飾符和刪除操作的回調函數。

## NodeScreen Composable
- 這個 composable 主要是用來構建整個筆記屏幕，包括標題、排序區域和筆記列表。
- 使用了 `Scaffold` 來構建整個屏幕結構，並包含浮動操作按鈕以及排序區域的動畫顯示。
- 支援參數包括導航控制器和視圖模型，並包含筆記的增刪操作和 Snackbar 的顯示。
 
# GetNote use case
- 新建了一個名為 GetNodeUseCase 的 use case，用於從資料庫中獲取單個節點。
- 這個 use case 包含一個 suspend 函數 invoke，接收節點的 ID 作為參數。
- 在函數內部，通過 repository 的方法來獲取指定 ID 的節點。
- 在這個 use case 中不需要進行額外的驗證，只是簡單地從資料庫中獲取節點。
# AddEditNoteViewModel
## 建立 AddEditNoteViewModel
在這段程式碼中，作者正在建立一個名為 `AddEditNoteViewModel` 的ViewModel。這個ViewModel負責管理用戶編輯或新增記事時的狀態和行為。

- 使用了Hilt依賴注入框架，並注入了 `NoteUseCases`（包含操作記事的 use cases）。
- 定義了一個預設的構造函數，接受 `NoteUseCases` 作為參數。

## 定義 ViewModel 狀態
在這段程式碼中，作者設定了 `AddEditNoteViewModel` 的狀態，包括用戶正在編輯的標題、內容以及選擇的顏色。

- 使用了 `State` 類型，分別定義了 `NoteTitleState`、`NoteContentState`、`NoteColorState` 三個狀態。
- 這些狀態將用於在Compose UI中控制相應的部分。
- 引入了一個 `SharedFlow` 類型的事件流（`uiEvent`），用於處理一次性的事件，如保存記事或顯示 Snackbar。
- 定義了一個 `uiEvent` 的 `SharedFlow`，並提供了一個公共的 `eventFlow`，以便在外部觀察事件。

## 定義事件類別
作者建立了一個事件類別 `UIEvent`，包括兩個具體的事件子類：`ShowSnackBar` 和 `SaveNode`。這些事件將用於通知 UI 處理相應的操作。

- `ShowSnackBar` 用於顯示 Snackbar，接受消息作為參數。
- `SaveNode` 用於通知保存記事。

## 實現 onEvent 函數
在這一部分，作者實現了 `onEvent` 函數，用於處理從 UI 接收到的事件。根據不同的事件類型，更新相應的 ViewModel 狀態或發出相應的事件。

- 根據不同的事件類型，更新標題、內容、顏色等狀態。
- 使用 `AddNode` use case 保存記事，並根據結果發出相應的事件，例如顯示 Snackbar 或返回上一層。

## 初始化 ViewModel 狀態
在 `init` 區塊中，作者使用 Hilt 注入 `SaveStateHandle` 並從中獲取導航參數。根據是否存在節點ID，來初始化 ViewModel 的狀態。

- 使用 `SaveStateHandle` 獲取導航參數中的節點ID。
- 如果節點ID存在，則使用 `GetNode` use case 獲取節點信息，並更新 ViewModel 狀態。
- 如果節點ID不存在（為-1），表示新建節點。

## 編輯現有節點
在 `onEvent` 中，作者處理了編輯現有節點的情況。如果有節點ID，則使用 `GetNode` use case 獲取節點信息，並將相應的信息設置到 ViewModel 的狀態中，以便在 UI 中顯示。 
 

# AddEditNoteScreen
## 建立 TransparentHintTextField
在這一部分，作者建立了一個名為 `TransparentHintTextField` 的組件，用於顯示具有透明提示的文本輸入框。這個組件需要多個參數，包括文本、提示、修飾符、提示是否可見、文本樣式、是否單行、焦點變化回調等。主要功能是在框中顯示文本並提供提示。

## 實現 AddEditNodeScreen
在這一部分，作者實現了名為 `AddEditNodeScreen` 的屏幕組件，用於編輯或新增節點。這個組件需要導航控制器、節點顏色和 `AddEditNoteViewModel` 作為參數。

- 獲取 ViewModel 的狀態和相關資訊，並設置節點的背景顏色。
- 使用 Scaffold、FloatingActionButton 和相關 UI 元素構建屏幕，並設置保存節點的動作。
- 創建顏色選擇器，使用 Box 顯示每個顏色作為一個圓形選擇器，並添加點擊事件來更改背景顏色。
- 添加文本輸入框，用於輸入節點標題和內容。

## 監聽事件流
最後，作者提到了監聽事件流的重要性。這是指在 ViewModel 中發送事件（如顯示 Snackbar）並在屏幕上觀察這些事件，以便執行相應的操作。這部分還未實現，但提到了在後續開發中需要處理的重要功能。
 
# Navigation setup
## 設置 UI 事件觸發
在這段程式碼中，作者設置了當特定事件發生時應觸發的相應UI行為。這包括顯示 Snackbar 和導航回上一個畫面。

- 使用 `LaunchedEffect` 块來確保某些行為僅執行一次。
- 通過 `collectLatest` 來收集最新的事件流，並根據事件類型執行相應的操作。
- 當收到 `showSnackBar` 事件時，顯示 Snackbar，並使用事件中的消息。
- 當收到 `saveNote` 事件時，通過 NavController 導航回上一個畫面。

## 設置導航
在這段程式碼中，作者設置了應用的導航系統，以便在不同的畫面之間進行切換。

- 定義了一個 `Screen` 類，其中包含應用中不同畫面的路徑。
- 使用 `NavController` 和 `NavHost` 來設置導航。
- 為不同的畫面設置路徑，並定義了當切換到某一畫面時應該傳遞的任何參數。


# Running the app
## 測試應用程式運行
在這段程式碼中，作者測試了應用程式的運行情況，並進行了一系列操作以確保各個功能正常。

- 點擊加號圖標後，成功切換到了新增/編輯筆記的畫面，並且可以自由更改顏色，動畫效果也運行良好。
- 成功創建了一個黃色筆記，並填寫了筆記內容，最後成功保存了筆記。
- 點擊已存在的筆記後，能夠正確加載並更新筆記內容與顏色。
- 成功測試了按標題降序排序筆記的功能，以及按日期升序排序筆記的功能。
- 順利刪除筆記並且成功使用了“復原”功能。



# 關鍵字
1. 乾淨架構（Clean Architecture）：軟體設計架構，確保程式碼的可測試性、可讀性和可維護性。
2. MVVM（Model-View-ViewModel）：將使用者介面與業務邏輯分開的軟體架構模式。
3. 依賴注入（Dependency Injection）：降低程式碼之間的耦合度的設計模式。
4. Room 資料庫：Android 平台上的持久性資料庫解決方案。
5. Jetpack Compose：Android 平台上的現代 UI 工具包，使用 Kotlin 語言編寫。
1. 乾淨架構（Clean Architecture）：由 Uncle Bob 提出的軟體架構方法，強調程式碼的可讀性、可測試性和可維護性。
2. MVVM（Model-View-ViewModel）：一種軟體架構模式，用於將使用者介面和業務邏輯分開。
3. SOLID 原則：由 Robert C. Martin 提出的五個面向物件程式設計的基本原則，包括單一職責原則、開放封閉原則、里式替換原則、介面分離原則和依賴反轉原則。
4. 使用案例（use cases）：在乾淨架構中，代表應用程式的使用情境，包括定義使用者的需求、功能和操作流程。
5. HTTP 函式庫：用於發送 HTTP 請求和處理 HTTP 響應的函式庫，例如 Retrofit 和 OkHttp。
1. 乾淨架構（Clean Architecture）：一種軟體架構設計方法，強調程式碼的模組化與可測試性。
2. Dependency Injection（DI）：依賴注入，一種設計模式，用於降低元件之間的耦合度。
3. 專案結構（Project Structure）：指程式碼檔案在專案中的組織方式和層次結構。
4. 功能（Feature）：指應用程式的一個具體功能或模組，通常包含一組相關的畫面或操作。
5. 通用層（Core Layer）：專案中的共用元件集合，包含多個功能模組所共用的程式碼和資源。
1. Room 資料庫：Android 平台上的持久性資料庫解決方案，基於 SQLite 構建，提供了一組方便的 API 用於數據操作。
2. 資料表（Entity）：在資料庫中表示特定類型物件的結構化資料集合。
3. 資料存取對象（DAO）：在 Room 中定義的介面，用於定義資料庫操作的方法，如插入、刪除、查詢等。
4. SQL 查詢：用於從資料庫擷取資料或對資料進行操作的結構化查詢語句。
5. 異步操作（Suspend Function）：在 Kotlin 中，用於非阻塞式的異步操作的函式，通常與協程一起使用。
1. Repository（資料庫）：用於將資料從不同資料來源（如資料庫、API）中擷取並提供給其他部分的類別。
2. DAO（資料存取對象）：用於將應用程式與資料庫之間的交互操作封裝起來的介面或類別。
3. 資料來源（Data Source）：資料的來源，可能是本地資料庫、遠端伺服器或其他應用程式。
4. 介面（Interface）：定義了類別應具備的方法和行為，但沒有提供實作細節。
1. Use Case（使用案例）：描述系統如何應對某個特定的使用者需求或情境的行為，代表了系統的一個功能。
2. Repository（資料庫）：用於將資料從不同資料來源（如資料庫、API）中擷取並提供給其他部分的類別。
3. 業務邏輯（Business Logic）：指應用程式中負責處理與業務相關的邏輯部分，例如資料驗證、規則處理等。
4. 模組化（Modularization）：將程式碼拆分成獨立的模組或功能單元，以便更容易維護和重用。
5. 重用性（Reusability）：程式碼或程式組件可以在不同的場景或應用中重複使用的能力。
1. DeleteNote Use Case：一個專門處理刪除節點業務邏輯的 Use Case，確保了刪除操作的模組化和可重用性。
2. 挂起函式（Suspend Function）：標記為 `suspend` 的函式表示可以挂起執行，通常用於處理異步操作，例如非同步呼叫或長時間執行的任務。
3. Repository 交互：指 Use Case 與 Repository 之間的互動，通常包括呼叫 Repository 提供的函式來執行資料庫操作。
1. 資料類別（Data Class）：在 Kotlin 中，資料類別用於保存和管理資料，通常用於簡單的資料封裝和傳遞，提供了自動生成 `equals()`、`hashCode()`、`toString()` 等方法的便利性。
2. 封裝（Encapsulation）：將相關的變數和函式打包在一起，並將其限制在一個邏輯單元中的過程，封裝有助於提高代碼的可維護性和可讀性。
3. ViewModel：在 Android 應用程式中，ViewModel 用於管理 UI 相關的資料，以及處理與 UI 顯示和互動相關的邏輯，例如獲取資料、處理使用者交互等。
1. 依賴注入（Dependency Injection，DI）：一種設計模式，用於管理元件之間的相依關係，將元件之間的相依性轉移到外部容器中，從而實現了組件之間的解耦和可測試性。
2. Dagger-Hilt：是由 Google 開發的 Android 平台上的依賴注入框架，旨在簡化 Android 應用程式中的依賴注入配置和管理。
3. 模組（Module）：在 Dagger-Hilt 中，模組用於提供依賴關係的實例，並通過在模組中的方法中使用 `@Provides` 或 `@Binds` 注解，來告訴 Dagger-Hilt 如何創建和提供依賴實例。
4. 單例（Singleton）：一種設計模式，確保在應用程式中只存在一個特定類型的實例，從而節省資源並確保一致性。
5. 清單檔（Manifest file）：在 Android 應用程式中，清單檔（AndroidManifest.xml）用於聲明應用程式的組件、權限需求和其他重要屬性，以便系統能夠管理應用程式的運行行為。
1. ViewModel：在 Android 中，ViewModel 是一個設計模式，用於管理和存儲與 UI 相關的數據以及處理 UI 狀態的邏輯，以確保 UI 的一致性和適當性。
2. UI 狀態（UI State）：指代在應用程式中與 UI 相關的數據和狀態，例如當前的顯示內容、使用者的操作等。
3. Use Cases：在軟體開發中，Use Cases 是描述系統功能與用戶互動的情景或案例。在本文中，NodeUseCases 是一個包含了所有與節點功能相關的 Use Cases 的類別。
4. 依賴注入（Dependency Injection）：是一種軟體設計模式，用於將相依性從高層模組移交給低層模組，以便提高程式碼的可擴展性和可測試性。
5. 清單檔（Manifest file）：在 Android 應用程式中，清單檔（AndroidManifest.xml）用於定義應用程式的基本屬性，例如應用程式的組件、權限和功能。
6. Cancellable Coroutine Job：是 Kotlin 中用於管理和取消 Coroutine 的任務，可用於在異步操作中執行一系列任務並控制其生命週期。
1. Composable: Composable 是 Jetpack Compose 中的一個概念，它是一個純函數，用於構建 UI 元件。每個 composable 函數都描述了 UI 的外觀和行為，並且可以根據需要進行組合和重用。
2. Canvas: Canvas 是 Jetpack Compose 中用於自定義繪製圖形和圖像的低級 API。通過 Canvas，開發人員可以直接控制畫布上的像素，從而實現各種高度定制的 UI 效果。
3. Scaffold: Scaffold 是 Jetpack Compose 中的一個組件，用於創建包含 App Bar、Drawer 和其他基本 UI 元素的應用程序頁面。它提供了許多常見的應用程序佈局和功能，使開發人員可以更快速地構建應用程序界面。
4. State: 在 Jetpack Compose 中，State 是一種特殊的數據類型，用於跟蹤 UI 的狀態變化。當 State 的值發生變化時，UI 將自動更新以反映新的狀態。
5. Modifier: Modifier 是 Jetpack Compose 中的一個類型，用於指定 UI 元件的外觀和行為。通過應用 Modifier，開發人員可以調整元件的大小、位置、填充等屬性，從而實現各種視覺效果和佈局結構。
6. Coroutine: Coroutine 是 Kotlin 中的一個異步編程框架，用於處理非阻塞和並發任務。在 Jetpack Compose 中，Coroutine 常用於處理 UI 事件、數據加載和其他需要異步處理的任務。
- Use Case: Use Case 是軟體開發中的一個常見概念，用於描述系統中的一個特定功能或行為。在這裡，GetNodeUseCase 就是一個用於獲取節點的特定功能。
- Suspend Function: Suspend 函數是 Kotlin 語言中的一種特殊類型的函數，它可以被暫停並恢復。在協程中，可以使用 suspend 修飾符來定義這種函數，用於執行可能會引起阻塞的異步操作，例如網絡請求或資料庫查詢。
- Repository: Repository 是一種設計模式，用於處理應用程序的數據存取邏輯。它負責從不同的數據來源（如資料庫、網絡服務等）中獲取數據，並將其提供給應用程序的其他部分。
- ID (Identifier): ID 是一個用於唯一標識某個實體或物件的識別符號。在這個情境中，ID 用於唯一標識節點，以便從資料庫中準確地檢索該節點的數據。
- ViewModel: 在 Android 中，ViewModel 是一個用於管理 UI 相關數據的架構元件，有助於保持數據的一致性和在配置更改（如旋轉屏幕）時保留數據。
- Hilt: Hilt 是一個由 Google 開發的 Android 依賴注入框架，用於更方便地實現依賴注入，提高代碼的可測性和可維護性。
- State: 在 Jetpack Compose 中，State 用於管理 UI 的可變數據。當狀態改變時，Compose 將自動重新渲染相應的 UI 部分。
- SharedFlow: SharedFlow 是 Kotlin 的一種流類型，用於發送一次性事件。在這裡，用於發送 UI 事件，如保存記事或顯示 Snackbar。
- Navigation Argument: 在 Android 中，Navigation Argument 是通過導航組件（如 Navigation Component）將數據傳遞給目標目的地的方法。在這裡，用於獲取節點ID。
- Coroutine: Kotlin 的一個異步編程框架，用於處理非同步任務，例如在 ViewModels 中的作用執行耗時的操作，而不會阻塞 UI。
- Snackbar: Android 中的一種通知元件，用於顯示簡短的消息或提示給用戶，通常用於顯示操作的結果或錯誤信息。
- ViewModel: 在 Android 中，ViewModel 是一個用於管理 UI 相關數據的架構元件，有助於保持數據的一致性和在配置更改（如旋轉屏幕）時保留數據。
- Hilt: Hilt 是一個由 Google 開發的 Android 依賴注入框架，用於更方便地實現依賴注入，提高代碼的可測性和可維護性。
- Scaffold: Scaffold 是一個 Compose UI 庫中的組件，用於構建基本的 Material Design 佈局，包括 AppBar、FloatingActionButton 和 SnackBar 等元素。
- Animatable: Animatable 是一個 Compose 中的動畫類型，用於創建可動畫的值。它允許對值進行平滑的過渡和動畫效果。
- Coroutine: Kotlin 的一個異步編程框架，用於處理非同步任務，例如在 ViewModels 中的作用執行耗時的操作，而不會阻塞 UI。
- Snackbar: Android 中的一種通知元件，用於顯示簡短的消息或提示給用戶，通常用於顯示操作的結果或錯誤信息。
- LaunchedEffect: 在 Compose 中，LaunchedEffect 是一個用於啟動副作用的組件，它確保只執行一次，並在組件首次啟動時觸發。
- collectLatest: 在 Kotlin 中，collectLatest 是一個用於 Flow 的擴展函數，用於收集最新的數據流事件，並在新事件到來時觸發相應的操作。
- Snackbar: Snackbar 是一種用於在應用界面底部顯示簡短消息的 Material Design 元件，通常用於顯示簡單的提示或反饋信息。
- NavController: NavController 是 Android Jetpack 中的一個組件，用於管理應用內部的導航，包括畫面之間的切換和傳遞參數。
- NavHost: NavHost 是一個用於顯示導航目的地的 Compose 元件，它管理著應用中不同畫面之間的導航關係。
- 路徑 (Route): 在應用的導航系統中，路徑指的是每個畫面或目的地的唯一識別符，用於在導航時確定應該顯示的畫面。
- 參數 (Arguments): 在導航中，參數是指在切換到某一畫面時傳遞給該畫面的任何額外信息或數據，以便該畫面進行相應的操作或顯示相應的內容。
- Snackbar: Snackbar 是一種用於顯示短暫消息或提示的 Material Design 元件，通常位於應用程式畫面的底部，用於向用戶提供信息或反饋。
- 應用程式測試: 測試應用程式的運行情況是一個重要的開發階段，通過模擬用戶操作並觀察系統的反應，可以發現潛在的錯誤或問題，從而改進應用程式的品質和穩定性。
- Snackbar 修改: Snackbar 的修改包括調整顯示效果、更改顏色和字體大小等，以提高其可讀性和視覺吸引力，從而更好地吸引用戶的注意力並提供清晰的提示信息。
- 觀眾互動: 作者鼓勵觀眾在影片下方留下評論、點贊和訂閱，這些互動可以幫助作者了解觀眾的反饋，並提供更多相關內容，同時也有助於建立社群和增加影片的曝光度。
- 付費課程: 作者提供付費課程，以提供更深入、更專業的知識和技能培訓，這些課程通常涵蓋更高級的主題和技術，並提供更多的學習資源和指導，有助於學習者提升技能和職業競爭力。
- ViewModel：在 Android 應用程式開發中，ViewModel 是一個設計模式，用於管理 UI 相關的數據以及處理與 UI 相關的邏輯。它通常與 LiveData 或其他觀察者模式結合使用，以便在 UI 和數據之間建立鏈接。
- Clean Architecture：一種軟體設計架構，旨在實現鬆散耦合、可測試性和可維護性。它將軟體系統分為不同的層次，包括表示層、業務邏輯層和資料層，並確保每一層都只依賴於更內部的層次。
- Use Case：在軟體開發中，Use Case 是指一個獨立的功能單元，用於實現系統的某個特定功能或業務需求。它通常代表著系統中的一個使用場景，並包含了相應的業務邏輯。
- Sealed Class：在 Kotlin 語言中，`sealed class` 是一種用於表示受限類型階層的特殊類型。它用於定義一組固定的子類別，這些子類別在同一文件中定義並且不能在其他文件中進行擴展。
- 協程（Coroutines）：在 Kotlin 語言中，協程是一種輕量級的並行處理方案，用於簡化非同步編程。它允許開發人員使用類似於同步編程的語法來處理非同步任務，同時提供了更好的性能和資源利用率。
- 不可變性（Immutability）：在軟體開發中，不可變性是指對象的狀態在創建後無法更改的特性。不可變的對象可以更容易地進行線程安全操作，並且減少了複雜性和錯誤的可能性。
- 競態條件（Race Condition）：在並行程式設計中，競態條件是指當多個執行緒或處理器同時訪問共享資源時可能發生的情況。這可能導致不可預測的行為和結果，並可能導致程式錯誤和錯誤行為。

-----
實作影片 [The Ultimate Guide to Android Testing (Unit Tests, UI Tests, End-to-End Tests) - Clean Architecture](https://www.youtube.com/watch?v=nDCCwyS0_MQ)

Github：[PhilippLackner-CleanArchitectureNoteApp](https://github.com/RainBowT0506/PhilippLackner-CleanArchitectureNoteApp)

# Why writing tests is important
## 重要性理解：
- 測試應用程式在軟體開發中的重要性不容小覷。作者指出測試在大型專案中特別關鍵，因為它能夠確保應用程式的穩定性和可靠性。
- 測試案例可以幫助開發者驗證應用程式的正確性，並且在需要時可以反覆執行，確保應用程式持續運作如預期。

## 為什麼寫測試是重要的：
- 比較初學者常常對於為什麼需要寫測試感到困惑。寫測試需要時間，尤其是對於初學者來說，寫測試很容易被認為是浪費時間的行為。
- 作者指出，測試案例的重要性在於它們提供了一種可靠的方法來確保應用程式的正常運行。尤其是在大型專案中，測試案例可以節省大量時間，因為開發者不需要手動測試每一個功能。

## 測試案例的優勢：
- 測試案例有助於發現應用程式中的錯誤和潛在問題，並且能夠提供快速反饋，以便開發者可以及早解決問題。
- 隨著應用程式的增長，測試案例的價值越來越明顯，因為它們可以確保即使在進行更改時，應用程式的其他部分也不會受到影響。


# The different types of tests
## 單元測試（Unit tests）：
- 單元測試是最小且最快速的測試類型，應佔大多數測試案例的比例，約佔 70%。
- 單元測試主要測試應用程式的單個組件，可以是單一類別或單一函式，不測試組件如何彼此合作。

## 整合測試（Integration tests）：
- 整合測試比單元測試更高一層，測試兩個類別彼此如何協同工作。
- 例如，如果測試點擊切換區塊時，區塊是否消失，再次點擊是否顯示，這將是一個整合測試。

## 端對端測試（End-to-end tests）：
- 端對端測試佔測試案例的約 10%，通常是較大的測試。
- 端對端測試模擬真實用戶行為，例如點擊按鈕、輸入內容、保存資料、驗證資料等，以確保應用程式的正常運作。 

# Explaining the test dependencies
## 添加測試相關依賴：
- 作者提到要開始編寫測試案例，首先需要為專案添加一些測試相關的依賴。
- 使用了 `testImplementation` 和 `androidTestImplementation` 兩種依賴配置，分別用於本地單元測試和Android儀器化測試。

## 本地單元測試相關依賴：
- `testImplementation` 是用於本地單元測試的依賴配置，其中包括了一些核心的測試相關依賴。
- 介紹了 JUnit 作為 Java 和 Kotlin 測試的框架，負責運行測試案例和確保初始設置正確。
- 提到 Truth，這是由 Google 提供的斷言庫，用於更美觀地進行斷言。
- 提到 MockWebServer，用於模擬網路響應，特別是當需要測試 API 時，可以模擬網路回應而無需實際進行網路呼叫。

## Android儀器化測試相關依賴：
- `androidTestImplementation` 用於 Android 儀器化測試，包含了使用 Hilt 進行測試和注入相依性的配置。
- 作者提到 Hilt 將用於撰寫測試案例並在測試案例中注入相依性。
- 其他相關依賴如 JUnit 和 Curtains（用於 Android 測試的庫），以及之前提到的 Truth 都被重複使用。

## 編碼前的步驟：
- 提到作者的 GitHub 存儲庫中有初始和最終的源代碼，並建議從初始代碼開始，因為最終的代碼將包含本教程中將展示的測試案例。
- 作者正在使用名為 "testing branch" 的分支，以便在該分支上撰寫測試案例。


# Writing our first unit test
## 撰寫第一個單元測試：
- 作者開始著手編寫第一個測試案例，選擇的是單元測試，因為它們相對較容易。
- 在清晰的架構專案中，單元測試通常針對業務邏輯進行，這裡主要針對使用案例（use cases）進行測試。

## 選擇使用案例（Use Case）進行測試：
- 在這個例子中，選擇了 `getNotes` 使用案例進行測試，這是一個較複雜的案例，主要是驗證當我們以不同排序方式呼叫時，是否能夠正確排序列表。

## 設定測試環境：
- 在測試案例中，作者使用了 JUnit 4 框架，並定義了測試類 `GetNotesTest`。
- 使用 `@Before` 標註的 `setup` 函數將在每個測試案例執行之前運行，這用於設置每個測試案例所需的物件。

## 建立假的資料庫（Fake Repository）：
- 由於在單元測試中，無法使用實際的資料庫或 API，作者創建了一個假的資料庫 `FakeNodeRepository`，它實作了原本的 `NodeRepository` 介面。
- 這個假的資料庫僅使用了一個簡單的列表，來模擬實際資料庫的行為。

## 準備測試數據：
- 為了測試排序功能，作者在 `setup` 函數中，初始化了一組範例節點，每個節點的標題都是 A 到 Z 的字母，並將它們插入到假的資料庫中。
- 使用 `shuffle` 函數將節點列表打亂，以確保它們不是按順序排列的。

## 撰寫測試案例：
- 作者開始撰寫測試案例，以驗證應用程式中的排序功能是否正確。
- 測試案例使用 JUnit 4 框架，將各個測試案例定義為函式，並使用特殊命名方式標示測試目的和期望結果。

## 準備測試環境：
- 使用 `@Test` 標註標示測試案例。
- 透過特殊的命名方式，清晰地表達測試案例的目的和期望結果，有助於理解和管理測試。

## 實作測試邏輯：
- 每個測試案例中，透過呼叫相應的使用案例，獲取待測資料，並使用 `assertThat` 函式進行斷言，確保期望結果與實際結果一致。

## 運行測試：
- 使用測試框架運行所有測試案例，以確保應用程式的功能正常且符合預期。
- 測試案例運行後，應該通過所有的斷言，否則表示有錯誤需要修復。
 
# Integration UI test
## 撰寫 Integration UI 測試：
- 作者將開始撰寫 Integration UI 測試，這種測試會測試節點畫面與檢視模型之間的互動。
- 測試案例將包含 UI 操作以及與檢視模型的互動，以確保功能的正確性。

## 準備測試環境：
- 使用 Hilt 來注入測試環境中的依賴。
- 創建測試模組，用於覆蓋應用程式的真實依賴，以確保測試環境的獨立性。
- 設置自定義測試運行器，以確保測試環境的正確配置。

## 設置 Hilt 規則與 Compose 測試規則：
- 使用 Hilt 規則來注入測試類別中的依賴。
- 使用 Compose 測試規則來設置測試中的 Compose 元件，確保可以模擬 UI 操作。

## 測試案例設置：
- 在測試案例中，使用 `@Before` 函式來設置測試環境。
- 透過 Hilt 規則注入依賴並設置 Compose 元件以模擬 UI 操作。 

## 撰寫 Integration UI 測試：
- 作者開始撰寫 Integration UI 測試，此類測試將驗證節點畫面與檢視模型之間的互動。
- 測試案例將模擬點擊特定圖示並確認排序區段是否展開。

## 撰寫測試函式：
- 建立測試函式時，作者使用下劃線來區分實際執行的操作和預期結果。
- 為了測試排序區段是否顯示，建立了名為 "click_toggle_order_section_is_visible" 的測試函式。

## 準備測試環境：
- 在準備測試環境時，需要使用 Hilt 規則來注入依賴，並使用 Compose 測試規則來設置 Compose 元件。
- 在 Compose 元件中，使用測試標籤來確保可以找到並操作需要測試的 UI 元件。

## 準備 NotesEndToEndTest：
- 作者在準備 NotesEndToEndTest 時，引入了 HiltAndroidRule 和 ComposeRule 以設置測試環境。
- 使用 HiltAndroidRule 來注入依賴，使用 ComposeRule 來建立 Compose 元件並設置測試內容。

## 建立測試環境：
- 在 setUp 函式中，作者注入了依賴並設置了 Compose 元件的測試環境。
- 使用 NavHost 來導航至 NotesScreen 和 AddEditNoteScreen 兩個屏幕，確保可以在這兩個屏幕間進行導航。 

# 撰寫測試用例 `saveNewNote_editAfterwards`
## **點擊新增按鈕進入新增記事畫面：**
- 使用 `composeRule` 的 `onNodeWithContentDescription` 找到新增按鈕（FAB），執行點擊操作。
  
## **輸入標題和內容：**
- 使用 `composeRule` 找到標題和內容的 `TextField`，並使用 `performTextInput` 方法輸入測試文字。
  
## **保存新記事：**
- 再次使用 `composeRule` 找到保存按鈕，執行點擊操作。

## **確認列表中是否顯示新記事：**
- 使用 `composeRule` 找到標題文字，進行 `assertIsDisplayed` 驗證。

## **進入編輯畫面：**
- 再次使用 `composeRule` 點擊標題文字，進入編輯畫面。

## **確認編輯畫面標題和內容：**
- 使用 `composeRule` 找到標題和內容的 `TextField`，進行 `assertTextEquals` 驗證。

## **對標題進行編輯：**
- 使用 `composeRule` 找到標題的 `TextField`，再次使用 `performTextInput` 添加文字 "2"。

## **更新記事：**
- 再次使用 `composeRule` 找到保存按鈕，執行點擊操作。

## **確認列表中是否顯示更新後的記事：**
- 使用 `composeRule` 找到更新後的標題文字，進行 `assertIsDisplayed` 驗證。 

 

# 撰寫測試用例 `saveNewNotes_orderByTitleDescending`
## **新增並排序記事：**
- 使用 for 迴圈新增三篇記事。
- 驗證這三篇記事是否在列表中顯示。

## **更改排序順序：**
- 點擊排序圖示，然後點擊相應的選項以更改排序順序（這裡是按標題降序排序）。

## **確認排序是否應用：**
- 驗證列表中的第一個節點是否為標題為 "3"，第二個節點為標題為 "2"，第三個節點為標題為 "1"。

# 問題
```
Execution failed for task ':app:processDebugAndroidTestManifest'.
> Could not resolve all files for configuration ':app:debugAndroidTestRuntimeClasspath'.
   > Could not find com.linkedin.dexmaker:dexmaker:2.21.0.
     Searched in the following locations:
     - https://dl.google.com/dl/android/maven2/com/linkedin/dexmaker/dexmaker/2.21.0/dexmaker-2.21.0.pom
     - https://repo.maven.apache.org/maven2/com/linkedin/dexmaker/dexmaker/2.21.0/dexmaker-2.21.0.pom
     Required by:
         project :app > io.mockk:mockk-android:1.10.5 > io.mockk:mockk-agent-android:1.10.5
```

我的 Kotlin Version:`1.5.21`
我先升級到 `1.5.32` 嘗試去升級 io.mockk lib
出現以下錯誤

```
This version (1.0.2) of the Compose Compiler requires Kotlin version 1.5.21 but you appear to be using Kotlin version 1.5.32 which is not known to be compatible.  Please fix your configuration (or `suppressKotlinVersionCompatibilityCheck` but don't say I didn't warn you!).
```

最後我把 Kotlin Version 降到 `1.5.31` 而 Compose Version 升到 `1.1.0-beta03`
因為 Compose Version 再上去所需要的 Kotlin Version 直接跳過 `1.5.32` 到 `1.6.0` 。

# 關鍵字
- **測試案例（Test cases）**：一組編寫好的指令，用於驗證軟體的特定功能或行為。測試案例能夠在預定的環境中模擬應用程式的使用情況，並且檢查程式的正確性和效能。
- **穩定性和可靠性（Stability and Reliability）**：指應用程式能夠在各種環境和條件下保持穩定運行，並且能夠提供一致可靠的功能。
- **自動化測試（Automated testing）**：透過自動化工具或腳本執行的測試過程，能夠減少人工測試的工作量並提高效率。
- **反饋（Feedback）**：指測試過程中獲得的結果和訊息，能夠幫助開發者了解應用程式的狀態並及時進行調整。
- **正常運行（Normal operation）**：應用程式在各種情況下都能夠正常運作，沒有出現錯誤或異常狀況。
- **持續整合（Continuous integration）**：一種開發方法，通過頻繁地整合代碼並自動執行測試來確保軟體品質和穩定性。
- **單元測試（Unit tests）**：測試應用程式中最小的可測試單元，通常是函式、方法或類別。這些測試通常是快速且獨立的，用於驗證單個組件的行為。
- **整合測試（Integration tests）**：測試不同組件或模組彼此如何協同工作。這些測試通常涉及測試多個組件之間的交互作用和集成。
- **端對端測試（End-to-end tests）**：測試整個應用程式的功能，模擬真實用戶在應用程式上執行的操作。這些測試通常包括模擬用戶在介面上的操作和驗證預期的結果。
- **`testImplementation` 和 `androidTestImplementation`**：Gradle 中的依賴配置，用於指定測試用例所需的相依性。`testImplementation` 適用於本地單元測試，而 `androidTestImplementation` 適用於 Android 測試。
- **JUnit**：Java 和 Kotlin 的測試框架，用於編寫和運行測試用例。
- **Truth**：由 Google 提供的斷言庫，用於簡化測試用例中的斷言語法，使其更易讀和管理。
- **MockWebServer**：一個用於模擬網絡響應的測試工具，通常用於測試 API 服務器，以測試應用程式對於不同響應的處理。
- **testImplementation**：Gradle 中的一個配置，用於指定僅在測試環境中使用的依賴項。
- **androidTestImplementation**：Gradle 中的一個配置，用於指定僅在 Android 儀器化測試環境中使用的依賴項。
- **JUnit**：Java 和 Kotlin 測試的常用框架，用於編寫和運行測試案例。
- **Truth**：由 Google 提供的斷言庫，用於撰寫更具可讀性和豐富的斷言。
- **MockWebServer**：用於模擬網路響應的測試庫，特別適用於需要測試 API 的情況。
- **Hilt**：由 Google 提供的依賴注入框架，用於 Android 項目中的依賴注入。
- **Curtains**：一個用於 Android 測試的庫，用於簡化測試中的操作。
- **Instrumentation Test**：Android 上的測試類型，涉及使用模擬器或實際設備來測試應用程式的行為。
- **單元測試（Unit test）**：針對軟體應用程式中的最小可測單元（通常是函數、方法或類別）進行的測試，目的是確保每個單元都能正確運作。
- **使用案例（Use Case）**：在軟體架構中，代表應用程式的一個功能或操作的抽象概念，通常包含了與業務邏輯相關的處理。
- **@Before**：JUnit 中的標註，用於標記在每個測試案例之前執行的設置函數。這通常用於初始化測試所需的物件。
- **Fake Repository**：在測試環境中使用的模擬資料庫，用於模擬實際資料庫的行為，並提供測試案例所需的數據。
- **shuffle**：打亂列表順序的函數，用於確保測試案例中的數據不是按順序排列的，以測試排序功能。
- **單元測試（Unit test）**：針對軟體應用程式中的最小可測單元進行的測試，以確保其功能正常且符合預期。
- **JUnit**：一種用於 Java 程式語言的單元測試框架，用於編寫和運行測試案例。
- **斷言（Assertion）**：在測試案例中，用於檢查實際結果是否與預期結果一致的方法或函式。
- **測試框架（Testing framework）**：一套用於自動化軟體測試的工具和函式庫，用於撰寫、組織和運行測試案例。
- **命名方式（Naming convention）**：在程式設計中，指定一套命名規則或慣例，以便程式碼易於閱讀和理解。
- **測試邏輯（Testing logic）**：測試案例中實際執行的測試程式碼，用於檢查待測程式的行為和功能。
- **測試套件（Test suite）**：一組相關的測試案例，用於測試特定的程式或模組的功能和行為。
- **Integration Test**：Integration 測試用於驗證不同系統元件之間的互動和整合，例如 UI 與檢視模型之間的互動。 
- **UI Test**：UI 測試用於驗證應用程式的用戶界面是否按預期運作，包括模擬用戶操作和檢查 UI 元素。
- **Hilt**：Hilt 是一個 Dagger 的延伸，用於實現依賴注入（DI）的 Android 框架。
- **Compose**：Jetpack Compose 是一個用於構建 Android 用戶界面的現代工具包。
- **規則（Rule）**：在 JUnit 測試中，規則用於對測試的執行行為進行定制，例如在測試之前或之後執行特定操作。
- **測試模組（Test Module）**：在測試中用於覆蓋或提供假依賴的模組，以確保測試環境的控制和獨立性。
- **測試標籤（Test Tag）**：測試標籤是在測試中使用的標識，用於確保測試可以準確地找到和操作需要測試的 UI 元件。
- **Compose 測試規則（Compose Test Rule）**：Compose 測試規則是用於設置 Compose 元件以進行 UI 測試的規則，可以模擬用戶操作並執行 UI 元件的斷言。
- **測試函式（Test Function）**：測試函式是用於執行單元測試或集成測試的函式，通常包含用於斷言或操作被測試代碼的邏輯。
- **斷言（Assertion）**：在測試中，斷言用於確保特定條件為真，若條件不滿足則測試失敗。
- **UI 元件（UI Component）**：UI 元件是構成用戶界面的基本元素，如按鈕、文本框、圖示等，用於與用戶進行互動。
- **模擬（Simulate）**：在測試中模擬行為或情境，以確保代碼在特定情況下的正確運作，如模擬用戶點擊、滾動等操作。
- **HiltAndroidRule**：HiltAndroidRule 是用於 Hilt 測試的規則，用於設置 Hilt 測試環境並注入依賴。
- **ComposeRule**：ComposeRule 是用於 Jetpack Compose 測試的規則，用於建立並設置 Compose 元件的測試環境。
- **NavHost**：NavHost 是 Navigation 組件中的一部分，用於在單個畫面中顯示多個目的地碎片並處理其導航邏輯。
- **ComposeRule**：ComposeRule 是用於 Jetpack Compose 測試的規則，用於建立並設置 Compose 元件的測試環境。
- **FAB（Floating Action Button）**：FAB 是浮動操作按鈕的縮寫，通常是一個浮動在應用程式畫面上的圓形按鈕，用於執行主要的操作。
- **TextField**：TextField 是 Compose 中的一個元件，用於接受和顯示用戶輸入的文本。
- **assertIsDisplayed**：`assertIsDisplayed` 是一個斷言，用於確保特定的 Compose 元件在畫面上可見。
- **performClick**：`performClick` 是一個 Compose Rule 的方法，用於模擬點擊操作。
- **assertTextEquals**：`assertTextEquals` 是一個斷言，用於確保指定的 Compose 元件的文本與預期值相符。
- **assertNodeWithTag**：`assertNodeWithTag` 是一個斷言，通常用於確保特定標籤的 Compose 元件存在。
- **performTextInput**：`performTextInput` 是一個 Compose Rule 的方法，用於模擬在 `TextField` 中進行文本輸入。
- **ContentDescription**：ContentDescription 是 Android 中的一種標示方式，用於描述 UI 元件的用途或內容，特別是對於無障礙功能的支援。
- **End-to-End Test**：End-to-End Test 是一種軟體測試，旨在測試整個應用程式流程，模擬真實用戶的操作，通常涵蓋多個屏幕和功能。
- **for 迴圈**：一種控制結構，用於重複執行一段程式碼，通常基於特定的條件或計數器。
- **排序圖示**：用於觸發對列表中項目進行排序的圖示，通常是上升或下降箭頭。
- **降序排序**：根據特定標準將項目按照遞減的順序排列，例如從大到小的數字或從 Z 到 A 的字母順序。
- **驗證**：在軟體測試中，確保系統的行為與預期一致的過程，通常通過斷言來實現。
- **節點**：在 UI 測試中，節點是指 UI 中的元素，例如按鈕、文字或圖像，可以通過標識符（例如文字或描述）在測試中進行識別和操作。
- **進入點**：測試用例中的某一部分，其中執行特定的測試步驟或操作。在本例中，每個進入點都代表著一個測試步驟，例如點擊按鈕或驗證文本。
- **測試用例**：定義了一系列測試步驟和預期結果，用於測試軟體的特定功能或行為。
- **composeRule**：Compose 測試庫中的一個類，用於設置和執行 Compose UI 元素的測試。
- **assertIsDisplayed**：一種測試斷言，用於確保特定的 UI 元素在畫面上可見。
- **performClick**：Compose 測試庫的方法之一，用於模擬對 UI 元素的點擊操作。
- **performTextInput**：Compose 測試庫的方法之一，用於模擬對 `TextField` 等文本輸入元素的文本輸入操作。
- **ContentDescription**：Android 中的一種設置，用於描述 UI 元素的內容和用途，尤其對於無障礙功能至關重要。
- **Node**：在 Compose 中表示 UI 元素的結構單元，例如按鈕、文本框等。

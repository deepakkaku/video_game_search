#(video_game_search_engine)

A simple Android app to search video games online using GiantBomb API.




Libraries Used
============================================================
  - Retrofit - to make http calls and run network operations on background thread asynchronously.
  - Picasso - to load image from URL into the imageViews


Structure
============================================================
MainActivity (Launcher Activity)
  - RecyclerView is used to load games inside CardView.
GameScreen (Game Details screen)
  - ImageView to load the cover image
  - WebView to show Description as it is returned in HTML format from the server

Orientation Changes are handled in both Activities.
Page Size = 10



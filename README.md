# Quicken Games (video_game_search_engine)

A simple Android app to search video games online using GiantBomb API.

Idealogy & Inspiration
============================================================
One of the things that I have heard from everyone and even my friends at QuickenLoans is the Culture.
I cannot explain how eager I am to be a part of such an esteem organization and contribute.
As one of the ISMs that is followed at QuickenLoans is Simplicity and that is what you will see in my app throughout.
Design is inspired from Material Design Cards with a very intuitive User interface and experience.
I believe that UI should be simple and to the point. UI is the way to communicate with your customer. Beautiful UI = Happy Customer.


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



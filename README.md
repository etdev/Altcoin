CryptsyTicker
=============

Altcoin Ticker Android Application using data from online cryptocurrency exchange Cryptsy.com

Features:
-Fetches current price data for various cryptocurrencies and displays it
-Price graphs with data from the last week (in progress)
-Quickly add multiple coins at once via checkboxes
-Price fetched via JSON, automatically persisted in SQLite Database on device

Files:

MainActivity, MainFragment
-Ticker Activity showing current prices for selected coins

SettingsActivity, SettingsFragment
-Activity where users can choose which coins to view on the main page

IndivActivity, IndivFragmentBot, IndivFragmentChart (In Progress)
-Activity showing detailed info on a given coin, including a 1-week price graph, volume, and current buy/sell orders

DatabaseHandler
-SQLite Helper class for storing info in case of restart, etc.

Pairs
-List of coins and their corresponding info on Cryptsy (for accessing from other activities)

URLFetch
-Simple code to retrieve the contents of a URL in String format

Market
-Data type matching the JSON data returned from Cryptsy's API

SingleFragmentActivity
-Generic 1-fragment Activity code






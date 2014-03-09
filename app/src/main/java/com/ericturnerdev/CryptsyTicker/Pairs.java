package com.ericturnerdev.CryptsyTicker;

import android.app.Application;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by ericturner on 2/26/14.
 */

public class Pairs extends Application {

    public static Hashtable<Integer, Market> pairs;

    public Pairs() {

        pairs = new Hashtable<Integer, Market>();

        pairs.put(112, new Market(112, "ASC", "XPM", "AsicCoin"));

        pairs.put(110, new Market(110, "COL", "XPM", "ColossusCoin"));

        pairs.put(122, new Market(122, "DVC", "XPM", "DevCoin"));

        pairs.put(105, new Market(105, "IFC", "XPM", "InfiniteCoin"));

        pairs.put(104, new Market(104, "NET", "XPM", "Netcoin"));

        pairs.put(103, new Market(103, "TIX", "XPM", "Tickets"));

        pairs.put(121, new Market(121, "ANC", "LTC", "AnonCoin"));

        pairs.put(111, new Market(111, "ASC", "LTC", "AsicCoin"));

        pairs.put(123, new Market(123, "CGB", "LTC", "CryptogenicBullion"));

        pairs.put(17, new Market(17, "CNC", "LTC", "CHNCoin"));

        pairs.put(109, new Market(109, "COL", "LTC", "ColossusCoin"));

        pairs.put(91, new Market(91, "CPR", "LTC", "CopperBars"));

        pairs.put(46, new Market(46, "DBL", "LTC", "Doubloons"));

        pairs.put(96, new Market(96, "DGC", "LTC", "DigitalCoin"));

        pairs.put(135, new Market(135, "DOGE", "LTC", "Dogecoin"));

        pairs.put(52, new Market(52, "DVC", "LTC", "DevCoin"));

        pairs.put(93, new Market(93, "ELP", "LTC", "ElephantCoin"));

        pairs.put(55, new Market(55, "EZC", "LTC", "EZCoin"));

        pairs.put(61, new Market(61, "FLO", "LTC", "FlorinCoin"));

        pairs.put(124, new Market(124, "FST", "LTC", "FastCoin"));

        pairs.put(36, new Market(36, "GLD", "LTC", "GoldCoin"));

        pairs.put(84, new Market(84, "GME", "LTC", "GameCoin"));

        pairs.put(60, new Market(60, "IFC", "LTC", "InfiniteCoin"));

        pairs.put(35, new Market(35, "JKC", "LTC", "JunkCoin"));

        pairs.put(100, new Market(100, "MEC", "LTC", "MegaCoin"));

        pairs.put(56, new Market(56, "MEM", "LTC", "MemeCoin"));

        pairs.put(145, new Market(145, "MOON", "LTC", "MoonCoin"));

        pairs.put(62, new Market(62, "MST", "LTC", "MasterCoin (Hydro)"));

        pairs.put(108, new Market(108, "NET", "LTC", "Netcoin"));

        pairs.put(125, new Market(125, "PPC", "LTC", "Peercoin"));

        pairs.put(101, new Market(101, "PXC", "LTC", "PhoenixCoin"));

        pairs.put(126, new Market(126, "QRK", "LTC", "Quark"));

        pairs.put(87, new Market(87, "RED", "LTC", "RedCoin"));

        pairs.put(37, new Market(37, "RYC", "LTC", "RoyalCoin"));

        pairs.put(128, new Market(128, "SBC", "LTC", "StableCoin"));

        pairs.put(98, new Market(98, "SXC", "LTC", "SexCoin"));

        pairs.put(147, new Market(147, "TIPS", "LTC", "FedoraCoin"));

        pairs.put(107, new Market(107, "TIX", "LTC", "Tickets"));

        pairs.put(21, new Market(21, "WDC", "LTC", "WorldCoin"));

        pairs.put(67, new Market(67, "XNC", "LTC", "XenCoin"));

        pairs.put(106, new Market(106, "XPM", "LTC", "PrimeCoin"));

        pairs.put(22, new Market(22, "YAC", "LTC", "YaCoin"));

        pairs.put(127, new Market(127, "ZET", "LTC", "ZetaCoin"));

        pairs.put(141, new Market(141, "42", "BTC", "42Coin"));

        pairs.put(57, new Market(57, "ALF", "BTC", "AlphaCoin"));

        pairs.put(43, new Market(43, "AMC", "BTC", "AmericanCoin"));

        pairs.put(66, new Market(66, "ANC", "BTC", "AnonCoin"));

        pairs.put(48, new Market(48, "ARG", "BTC", "Argentum"));

        pairs.put(142, new Market(142, "BCX", "BTC", "BattleCoin"));

        pairs.put(157, new Market(157, "BEN", "BTC", "Benjamins"));

        pairs.put(129, new Market(129, "BET", "BTC", "Betacoin"));

        pairs.put(10, new Market(10, "BQC", "BTC", "BBQCoin"));

        pairs.put(23, new Market(23, "BTB", "BTC", "BitBar"));

        pairs.put(49, new Market(49, "BTE", "BTC", "ByteCoin"));

        pairs.put(50, new Market(50, "BTG", "BTC", "BitGem"));

        pairs.put(102, new Market(102, "BUK", "BTC", "CryptoBuck"));

        pairs.put(154, new Market(154, "CACH", "BTC", "CACHeCoin"));

        pairs.put(53, new Market(53, "CAP", "BTC", "BottleCaps"));

        pairs.put(150, new Market(150, "CASH", "BTC", "CashCoin"));

        pairs.put(136, new Market(136, "CAT", "BTC", "CatCoin"));

        pairs.put(70, new Market(70, "CGB", "BTC", "CryptogenicBullion"));

        pairs.put(95, new Market(95, "CLR", "BTC", "CopperLark"));

        pairs.put(74, new Market(74, "CMC", "BTC", "Cosmoscoin"));

        pairs.put(8, new Market(8, "CNC", "BTC", "CHNCoin"));

        pairs.put(58, new Market(58, "CRC", "BTC", "CraftCoin"));

        pairs.put(68, new Market(68, "CSC", "BTC", "CasinoCoin"));

        pairs.put(131, new Market(131, "DEM", "BTC", "eMark"));

        pairs.put(26, new Market(26, "DGC", "BTC", "DigitalCoin"));

        pairs.put(72, new Market(72, "DMD", "BTC", "Diamond"));

        pairs.put(132, new Market(132, "DOGE", "BTC", "Dogecoin"));

        pairs.put(155, new Market(155, "DRK", "BTC", "DarkCoin"));

        pairs.put(40, new Market(40, "DVC", "BTC", "DevCoin"));

        pairs.put(139, new Market(139, "EAC", "BTC", "EarthCoin"));

        pairs.put(12, new Market(12, "ELC", "BTC", "ElaCoin"));

        pairs.put(69, new Market(69, "EMD", "BTC", "Emerald"));

        pairs.put(47, new Market(47, "EZC", "BTC", "EZCoin"));

        pairs.put(138, new Market(138, "FFC", "BTC", "FireflyCoin"));

        pairs.put(39, new Market(39, "FRC", "BTC", "FreiCoin"));

        pairs.put(33, new Market(33, "FRK", "BTC", "Franko"));

        pairs.put(44, new Market(44, "FST", "BTC", "FastCoin"));

        pairs.put(5, new Market(5, "FTC", "BTC", "FeatherCoin"));

        pairs.put(82, new Market(82, "GDC", "BTC", "GrandCoin"));

        pairs.put(76, new Market(76, "GLC", "BTC", "Globalcoin"));

        pairs.put(30, new Market(30, "GLD", "BTC", "GoldCoin"));

        pairs.put(78, new Market(78, "GLX", "BTC", "Galaxycoin"));

        pairs.put(80, new Market(80, "HBN", "BTC", "HoboNickels"));

        pairs.put(59, new Market(59, "IFC", "BTC", "InfiniteCoin"));

        pairs.put(38, new Market(38, "IXC", "BTC", "IXCoin"));

        pairs.put(25, new Market(25, "JKC", "BTC", "JunkCoin"));

        pairs.put(65, new Market(65, "KGC", "BTC", "KrugerCoin"));

        pairs.put(148, new Market(148, "LEAF", "BTC", "LeafCoin"));

        pairs.put(116, new Market(116, "LK7", "BTC", "Lucky7Coin"));

        pairs.put(34, new Market(34, "LKY", "BTC", "LuckyCoin"));

        pairs.put(137, new Market(137, "LOT", "BTC", "LottoCoin"));

        pairs.put(3, new Market(3, "LTC", "BTC", "LiteCoin"));

        pairs.put(152, new Market(152, "MAX", "BTC", "MaxCoin"));

        pairs.put(45, new Market(45, "MEC", "BTC", "MegaCoin"));

        pairs.put(149, new Market(149, "MEOW", "BTC", "KittehCoin"));

        pairs.put(156, new Market(156, "MINT", "BTC", "MintCoin"));

        pairs.put(7, new Market(7, "MNC", "BTC", "MinCoin"));

        pairs.put(146, new Market(146, "MOON", "BTC", "MoonCoin"));

        pairs.put(64, new Market(64, "NAN", "BTC", "NanoToken"));

        pairs.put(32, new Market(32, "NBL", "BTC", "Nibble"));

        pairs.put(90, new Market(90, "NEC", "BTC", "NeoCoin"));

        pairs.put(134, new Market(134, "NET", "BTC", "Netcoin"));

        pairs.put(29, new Market(29, "NMC", "BTC", "NameCoin"));

        pairs.put(54, new Market(54, "NRB", "BTC", "NoirBits"));

        pairs.put(13, new Market(13, "NVC", "BTC", "NovaCoin"));

        pairs.put(75, new Market(75, "ORB", "BTC", "Orbitcoin"));

        pairs.put(144, new Market(144, "OSC", "BTC", "OpenSourceCoin"));

        pairs.put(86, new Market(86, "PHS", "BTC", "PhilosopherStone"));

        pairs.put(120, new Market(120, "Points", "BTC", "CryptsyPoints"));

        pairs.put(28, new Market(28, "PPC", "BTC", "Peercoin"));

        pairs.put(119, new Market(119, "PTS", "BTC", "BitShares"));

        pairs.put(31, new Market(31, "PXC", "BTC", "PhoenixCoin"));

        pairs.put(92, new Market(92, "PYC", "BTC", "PayCoin"));

        pairs.put(71, new Market(71, "QRK", "BTC", "Quark"));

        pairs.put(143, new Market(143, "RPC", "BTC", "RonPaulCoin"));

        pairs.put(51, new Market(51, "SBC", "BTC", "StableCoin"));

        pairs.put(158, new Market(158, "SMC", "BTC", "SmartCoin"));

        pairs.put(81, new Market(81, "SPT", "BTC", "Spots"));

        pairs.put(88, new Market(88, "SRC", "BTC", "SecureCoin"));

        pairs.put(83, new Market(83, "STR", "BTC", "StarCoin"));

        pairs.put(153, new Market(153, "SXC", "BTC", "SexCoin"));

        pairs.put(117, new Market(117, "TAG", "BTC", "TagCoin"));

        pairs.put(114, new Market(114, "TEK", "BTC", "TekCoin"));

        pairs.put(130, new Market(130, "TGC", "BTC", "TigerCoin"));

        pairs.put(27, new Market(27, "TRC", "BTC", "TerraCoin"));

        pairs.put(133, new Market(133, "UNO", "BTC", "Unobtanium"));

        pairs.put(151, new Market(151, "VTC", "BTC", "VertCoin"));

        pairs.put(14, new Market(14, "WDC", "BTC", "WorldCoin"));

        pairs.put(115, new Market(115, "XJO", "BTC", "JouleCoin"));

        pairs.put(63, new Market(63, "XPM", "BTC", "PrimeCoin"));

        pairs.put(11, new Market(11, "YAC", "BTC", "YaCoin"));

        pairs.put(73, new Market(73, "YBC", "BTC", "YBCoin"));

        pairs.put(140, new Market(140, "ZCC", "BTC", "ZcCoin"));

        pairs.put(85, new Market(85, "ZET", "BTC", "ZetaCoin"));

    }

    public static Market getMarket(Integer id) {

        return pairs.get(id);

    }

    public static ArrayList<Market> getAllMarkets() {

        return new ArrayList<Market>(pairs.values());

    }

    public static ArrayList<Market> getVisibleMarkets() {

        ArrayList<Market> temp = new ArrayList<Market>();

        for (Market m : getAllMarkets()) {

            if (m.isVisible()) {
                temp.add(m);
            }

        }

        return temp;

    }


}

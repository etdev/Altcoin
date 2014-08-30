package com.ericturnerdev.Altcoin;

import android.app.Application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Eric Turner (ericturnerdev) on 2/26/14.  This is a list of coins that
 * can be accessed from any activity
 */

public class Pairs extends Application {

    public static HashMap<Integer, Market> pairs;

    public Pairs() {

        pairs = new HashMap<Integer, Market>();

        //pairs.put(500, new Market(500, "BTC", "USD", "Bitcoin"));

        pairs.put(501, new Market(501, "BTC", "EUR", "Bitcoin"));

        pairs.put(502, new Market(502, "BTC", "CNY", "Bitcoin"));

        //Added via PHP script (ericturner)


        pairs.put(2, new Market(2, "BTC", "USD", "BitCoin"));

        pairs.put(182, new Market(182, "DOGE", "USD", "Dogecoin"));

        pairs.put(213, new Market(213, "DRK", "USD", "DarkCoin"));

        pairs.put(6, new Market(6, "FTC", "USD", "FeatherCoin"));

        pairs.put(1, new Market(1, "LTC", "USD", "LiteCoin"));

        pairs.put(262, new Market(262, "RDD", "USD", "ReddCoin"));

        pairs.put(94, new Market(94, "ADT", "LTC", "AndroidsTokensV2"));

        pairs.put(121, new Market(121, "ANC", "LTC", "AnonCoin"));

        pairs.put(111, new Market(111, "ASC", "LTC", "AsicCoin"));

        pairs.put(161, new Market(161, "AUR", "LTC", "AuroraCoin"));

        pairs.put(186, new Market(186, "BAT", "LTC", "BatCoin"));

        pairs.put(191, new Market(191, "BC", "LTC", "BlackCoin"));

        pairs.put(123, new Market(123, "CGB", "LTC", "CryptogenicBullion"));

        pairs.put(228, new Market(228, "CLOAK", "LTC", "CloakCoin"));

        pairs.put(17, new Market(17, "CNC", "LTC", "CHNCoin"));

        pairs.put(109, new Market(109, "COL", "LTC", "ColossusCoin"));

        pairs.put(91, new Market(91, "CPR", "LTC", "CopperBars"));

        pairs.put(220, new Market(220, "CRYPT", "LTC", "CryptCoin"));

        pairs.put(175, new Market(175, "CTM", "LTC", "Continuumcoin"));

        pairs.put(46, new Market(46, "DBL", "LTC", "Doubloons"));

        pairs.put(96, new Market(96, "DGC", "LTC", "DigitalCoin"));

        pairs.put(206, new Market(206, "DIME", "LTC", "DimeCoin"));

        pairs.put(194, new Market(194, "DMC", "LTC", "DamaCoin"));

        pairs.put(135, new Market(135, "DOGE", "LTC", "Dogecoin"));

        pairs.put(214, new Market(214, "DRK", "LTC", "DarkCoin"));

        pairs.put(52, new Market(52, "DVC", "LTC", "DevCoin"));

        pairs.put(244, new Market(244, "EAC", "LTC", "EarthCoin"));

        pairs.put(93, new Market(93, "ELP", "LTC", "ElephantCoin"));

        pairs.put(55, new Market(55, "EZC", "LTC", "EZCoin"));

        pairs.put(246, new Market(246, "FLAP", "LTC", "FlappyCoin"));

        pairs.put(61, new Market(61, "FLO", "LTC", "FlorinCoin"));

        pairs.put(171, new Market(171, "FRK", "LTC", "Franko"));

        pairs.put(124, new Market(124, "FST", "LTC", "FastCoin"));

        pairs.put(4, new Market(4, "FTC", "LTC", "FeatherCoin"));

        pairs.put(36, new Market(36, "GLD", "LTC", "GoldCoin"));

        pairs.put(230, new Market(230, "GLYPH", "LTC", "GlyphCoin"));

        pairs.put(84, new Market(84, "GME", "LTC", "GameCoin"));

        pairs.put(242, new Market(242, "GUE", "LTC", "GuerillaCoin"));

        pairs.put(60, new Market(60, "IFC", "LTC", "InfiniteCoin"));

        pairs.put(193, new Market(193, "KARM", "LTC", "KarmaCoin"));

        pairs.put(245, new Market(245, "LEAF", "LTC", "LeafCoin"));

        pairs.put(243, new Market(243, "LOT", "LTC", "LottoCoin"));

        pairs.put(234, new Market(234, "LTCX", "LTC", "LiteCoinX"));

        pairs.put(218, new Market(218, "MAX", "LTC", "MaxCoin"));

        pairs.put(100, new Market(100, "MEC", "LTC", "MegaCoin"));

        pairs.put(56, new Market(56, "MEM", "LTC", "MemeCoin"));

        pairs.put(231, new Market(231, "MEOW", "LTC", "KittehCoin"));

        pairs.put(145, new Market(145, "MOON", "LTC", "MoonCoin"));

        pairs.put(62, new Market(62, "MST", "LTC", "MasterCoin (Hydro)"));

        pairs.put(108, new Market(108, "NET", "LTC", "Netcoin"));

        pairs.put(162, new Market(162, "NXT", "LTC", "Nxt"));

        pairs.put(263, new Market(263, "NYAN", "LTC", "NyanCoin"));

        pairs.put(125, new Market(125, "PPC", "LTC", "Peercoin"));

        pairs.put(101, new Market(101, "PXC", "LTC", "PhoenixCoin"));

        pairs.put(126, new Market(126, "QRK", "LTC", "Quark"));

        pairs.put(190, new Market(190, "RBBT", "LTC", "RabbitCoin"));

        pairs.put(212, new Market(212, "RDD", "LTC", "ReddCoin"));

        pairs.put(87, new Market(87, "RED", "LTC", "RedCoin"));

        pairs.put(238, new Market(238, "RZR", "LTC", "Razor"));

        pairs.put(128, new Market(128, "SBC", "LTC", "StableCoin"));

        pairs.put(240, new Market(240, "SUPER", "LTC", "SuperCoin"));

        pairs.put(98, new Market(98, "SXC", "LTC", "SexCoin"));

        pairs.put(224, new Market(224, "TES", "LTC", "TeslaCoin"));

        pairs.put(147, new Market(147, "TIPS", "LTC", "FedoraCoin"));

        pairs.put(107, new Market(107, "TIX", "LTC", "Tickets"));

        pairs.put(215, new Market(215, "VRC", "LTC", "VeriCoin"));

        pairs.put(217, new Market(217, "VTC", "LTC", "VertCoin"));

        pairs.put(21, new Market(21, "WDC", "LTC", "WorldCoin"));

        pairs.put(216, new Market(216, "XC", "LTC", "X11Coin"));

        pairs.put(67, new Market(67, "XNC", "LTC", "XenCoin"));

        pairs.put(106, new Market(106, "XPM", "LTC", "PrimeCoin"));

        pairs.put(22, new Market(22, "YAC", "LTC", "YaCoin"));

        pairs.put(176, new Market(176, "ZEIT", "LTC", "ZeitCoin"));

        pairs.put(127, new Market(127, "ZET", "LTC", "ZetaCoin"));

        pairs.put(141, new Market(141, "42", "BTC", "42Coin"));

        pairs.put(199, new Market(199, "AC", "BTC", "AsiaCoin"));

        pairs.put(253, new Market(253, "AGS", "BTC", "Aegis"));

        pairs.put(57, new Market(57, "ALF", "BTC", "AlphaCoin"));

        pairs.put(273, new Market(273, "ALN", "BTC", "AlienCoin"));

        pairs.put(43, new Market(43, "AMC", "BTC", "AmericanCoin"));

        pairs.put(66, new Market(66, "ANC", "BTC", "AnonCoin"));

        pairs.put(257, new Market(257, "APEX", "BTC", "ApexCoin"));

        pairs.put(48, new Market(48, "ARG", "BTC", "Argentum"));

        pairs.put(160, new Market(160, "AUR", "BTC", "AuroraCoin"));

        pairs.put(179, new Market(179, "BC", "BTC", "BlackCoin"));

        pairs.put(142, new Market(142, "BCX", "BTC", "BattleCoin"));

        pairs.put(157, new Market(157, "BEN", "BTC", "Benjamins"));

        pairs.put(129, new Market(129, "BET", "BTC", "Betacoin"));

        pairs.put(251, new Market(251, "BLU", "BTC", "BlueCoin"));

        pairs.put(10, new Market(10, "BQC", "BTC", "BBQCoin"));

        pairs.put(23, new Market(23, "BTB", "BTC", "BitBar"));

        pairs.put(256, new Market(256, "BTCD", "BTC", "BitcoinDark"));

        pairs.put(49, new Market(49, "BTE", "BTC", "ByteCoin"));

        pairs.put(50, new Market(50, "BTG", "BTC", "BitGem"));

        pairs.put(102, new Market(102, "BUK", "BTC", "CryptoBuck"));

        pairs.put(154, new Market(154, "CACH", "BTC", "CACHeCoin"));

        pairs.put(221, new Market(221, "CAIx", "BTC", "CAIx"));

        pairs.put(53, new Market(53, "CAP", "BTC", "BottleCaps"));

        pairs.put(150, new Market(150, "CASH", "BTC", "CashCoin"));

        pairs.put(136, new Market(136, "CAT", "BTC", "CatCoin"));

        pairs.put(70, new Market(70, "CGB", "BTC", "CryptogenicBullion"));

        pairs.put(197, new Market(197, "CINNI", "BTC", "CinniCoin"));

        pairs.put(227, new Market(227, "CLOAK", "BTC", "CloakCoin"));

        pairs.put(95, new Market(95, "CLR", "BTC", "CopperLark"));

        pairs.put(74, new Market(74, "CMC", "BTC", "Cosmoscoin"));

        pairs.put(8, new Market(8, "CNC", "BTC", "CHNCoin"));

        pairs.put(260, new Market(260, "CNL", "BTC", "ConcealCoin"));

        pairs.put(198, new Market(198, "COMM", "BTC", "CommunityCoin"));

        pairs.put(266, new Market(266, "COOL", "BTC", "CoolCoin"));

        pairs.put(58, new Market(58, "CRC", "BTC", "CraftCoin"));

        pairs.put(219, new Market(219, "CRYPT", "BTC", "CryptCoin"));

        pairs.put(68, new Market(68, "CSC", "BTC", "CasinoCoin"));

        pairs.put(131, new Market(131, "DEM", "BTC", "eMark"));

        pairs.put(167, new Market(167, "DGB", "BTC", "Digibyte"));

        pairs.put(26, new Market(26, "DGC", "BTC", "DigitalCoin"));

        pairs.put(72, new Market(72, "DMD", "BTC", "Diamond"));

        pairs.put(132, new Market(132, "DOGE", "BTC", "Dogecoin"));

        pairs.put(155, new Market(155, "DRK", "BTC", "DarkCoin"));

        pairs.put(274, new Market(274, "DRKC", "BTC", "DarkCash"));

        pairs.put(40, new Market(40, "DVC", "BTC", "DevCoin"));

        pairs.put(139, new Market(139, "EAC", "BTC", "EarthCoin"));

        pairs.put(12, new Market(12, "ELC", "BTC", "ElaCoin"));

        pairs.put(188, new Market(188, "EMC2", "BTC", "Einsteinium"));

        pairs.put(69, new Market(69, "EMD", "BTC", "Emerald"));

        pairs.put(183, new Market(183, "EXE", "BTC", "ExeCoin"));

        pairs.put(47, new Market(47, "EZC", "BTC", "EZCoin"));

        pairs.put(138, new Market(138, "FFC", "BTC", "FireflyCoin"));

        pairs.put(192, new Market(192, "FLT", "BTC", "FlutterCoin"));

        pairs.put(259, new Market(259, "FRAC", "BTC", "FractalCoin"));

        pairs.put(39, new Market(39, "FRC", "BTC", "FreiCoin"));

        pairs.put(33, new Market(33, "FRK", "BTC", "Franko"));

        pairs.put(44, new Market(44, "FST", "BTC", "FastCoin"));

        pairs.put(5, new Market(5, "FTC", "BTC", "FeatherCoin"));

        pairs.put(283, new Market(283, "GB", "BTC", "Greenbacks"));

        pairs.put(82, new Market(82, "GDC", "BTC", "GrandCoin"));

        pairs.put(76, new Market(76, "GLC", "BTC", "Globalcoin"));

        pairs.put(30, new Market(30, "GLD", "BTC", "GoldCoin"));

        pairs.put(78, new Market(78, "GLX", "BTC", "Galaxycoin"));

        pairs.put(229, new Market(229, "GLYPH", "BTC", "GlyphCoin"));

        pairs.put(277, new Market(277, "GML", "BTC", "GameLeagueCoin"));

        pairs.put(241, new Market(241, "GUE", "BTC", "GuerillaCoin"));

        pairs.put(281, new Market(281, "HAL", "BTC", "Halcyon"));

        pairs.put(80, new Market(80, "HBN", "BTC", "HoboNickels"));

        pairs.put(249, new Market(249, "HUC", "BTC", "HunterCoin"));

        pairs.put(185, new Market(185, "HVC", "BTC", "HeavyCoin"));

        pairs.put(267, new Market(267, "ICB", "BTC", "IcebergCoin"));

        pairs.put(59, new Market(59, "IFC", "BTC", "InfiniteCoin"));

        pairs.put(272, new Market(272, "IOC", "BTC", "I/OCoin"));

        pairs.put(38, new Market(38, "IXC", "BTC", "IXCoin"));

        pairs.put(25, new Market(25, "JKC", "BTC", "JunkCoin"));

        pairs.put(269, new Market(269, "JUDGE", "BTC", "JudgeCoin"));

        pairs.put(178, new Market(178, "KDC", "BTC", "KlondikeCoin"));

        pairs.put(255, new Market(255, "KEY", "BTC", "KeyCoin"));

        pairs.put(65, new Market(65, "KGC", "BTC", "KrugerCoin"));

        pairs.put(204, new Market(204, "LGD", "BTC", "LegendaryCoin"));

        pairs.put(116, new Market(116, "LK7", "BTC", "Lucky7Coin"));

        pairs.put(34, new Market(34, "LKY", "BTC", "LuckyCoin"));

        pairs.put(202, new Market(202, "LTB", "BTC", "LiteBar"));

        pairs.put(3, new Market(3, "LTC", "BTC", "LiteCoin"));

        pairs.put(233, new Market(233, "LTCX", "BTC", "LiteCoinX"));

        pairs.put(282, new Market(282, "LXC", "BTC", "LibrexCoin"));

        pairs.put(177, new Market(177, "LYC", "BTC", "LycanCoin"));

        pairs.put(152, new Market(152, "MAX", "BTC", "MaxCoin"));

        pairs.put(45, new Market(45, "MEC", "BTC", "MegaCoin"));

        pairs.put(275, new Market(275, "MED", "BTC", "Mediterraneancoin"));

        pairs.put(258, new Market(258, "MIN", "BTC", "Minerals"));

        pairs.put(156, new Market(156, "MINT", "BTC", "MintCoin"));

        pairs.put(187, new Market(187, "MN1", "BTC", "Mining Contract 1"));

        pairs.put(196, new Market(196, "MN2", "BTC", "Mining Contract 2"));

        pairs.put(7, new Market(7, "MNC", "BTC", "MinCoin"));

        pairs.put(189, new Market(189, "MRY", "BTC", "MurrayCoin"));

        pairs.put(200, new Market(200, "MYR", "BTC", "MyriadCoin"));

        pairs.put(164, new Market(164, "MZC", "BTC", "MazaCoin"));

        pairs.put(64, new Market(64, "NAN", "BTC", "NanoToken"));

        pairs.put(207, new Market(207, "NAUT", "BTC", "NautilusCoin"));

        pairs.put(252, new Market(252, "NAV", "BTC", "NavajoCoin"));

        pairs.put(32, new Market(32, "NBL", "BTC", "Nibble"));

        pairs.put(90, new Market(90, "NEC", "BTC", "NeoCoin"));

        pairs.put(134, new Market(134, "NET", "BTC", "Netcoin"));

        pairs.put(29, new Market(29, "NMC", "BTC", "NameCoin"));

        pairs.put(264, new Market(264, "NOBL", "BTC", "NobleCoin"));

        pairs.put(54, new Market(54, "NRB", "BTC", "NoirBits"));

        pairs.put(211, new Market(211, "NRS", "BTC", "NoirShares"));

        pairs.put(13, new Market(13, "NVC", "BTC", "NovaCoin"));

        pairs.put(159, new Market(159, "NXT", "BTC", "Nxt"));

        pairs.put(184, new Market(184, "NYAN", "BTC", "NyanCoin"));

        pairs.put(75, new Market(75, "ORB", "BTC", "Orbitcoin"));

        pairs.put(144, new Market(144, "OSC", "BTC", "OpenSourceCoin"));

        pairs.put(86, new Market(86, "PHS", "BTC", "PhilosopherStone"));

        pairs.put(120, new Market(120, "Points", "BTC", "CryptsyPoints"));

        pairs.put(173, new Market(173, "POT", "BTC", "PotCoin"));

        pairs.put(28, new Market(28, "PPC", "BTC", "Peercoin"));

        pairs.put(268, new Market(268, "PSEUD", "BTC", "PseudoCoin"));

        pairs.put(119, new Market(119, "PTS", "BTC", "Bitshares PTS"));

        pairs.put(31, new Market(31, "PXC", "BTC", "PhoenixCoin"));

        pairs.put(92, new Market(92, "PYC", "BTC", "PayCoin"));

        pairs.put(71, new Market(71, "QRK", "BTC", "Quark"));

        pairs.put(169, new Market(169, "RDD", "BTC", "ReddCoin"));

        pairs.put(284, new Market(284, "RIPO", "BTC", "RipoffCoin"));

        pairs.put(143, new Market(143, "RPC", "BTC", "RonPaulCoin"));

        pairs.put(235, new Market(235, "RT2", "BTC", "RotoCoin"));

        pairs.put(9, new Market(9, "RYC", "BTC", "RoyalCoin"));

        pairs.put(237, new Market(237, "RZR", "BTC", "Razor"));

        pairs.put(232, new Market(232, "SAT2", "BTC", "SaturnCoinV2"));

        pairs.put(51, new Market(51, "SBC", "BTC", "StableCoin"));

        pairs.put(270, new Market(270, "SFR", "BTC", "SaffronCoin"));

        pairs.put(248, new Market(248, "SHLD", "BTC", "ShieldCoin"));

        pairs.put(225, new Market(225, "SILK", "BTC", "SilkCoin"));

        pairs.put(158, new Market(158, "SMC", "BTC", "SmartCoin"));

        pairs.put(276, new Market(276, "SOLE", "BTC", "SoleCoin"));

        pairs.put(180, new Market(180, "SPA", "BTC", "SpainCoin"));

        pairs.put(81, new Market(81, "SPT", "BTC", "Spots"));

        pairs.put(88, new Market(88, "SRC", "BTC", "SecureCoin"));

        pairs.put(83, new Market(83, "STR", "BTC", "StarCoin"));

        pairs.put(239, new Market(239, "SUPER", "BTC", "SuperCoin"));

        pairs.put(153, new Market(153, "SXC", "BTC", "SexCoin"));

        pairs.put(271, new Market(271, "SYNC", "BTC", "Sync"));

        pairs.put(278, new Market(278, "SYS", "BTC", "SysCoin"));

        pairs.put(117, new Market(117, "TAG", "BTC", "TagCoin"));

        pairs.put(166, new Market(166, "TAK", "BTC", "TakCoin"));

        pairs.put(114, new Market(114, "TEK", "BTC", "TekCoin"));

        pairs.put(223, new Market(223, "TES", "BTC", "TeslaCoin"));

        pairs.put(130, new Market(130, "TGC", "BTC", "TigerCoin"));

        pairs.put(250, new Market(250, "TOR", "BTC", "TorCoin"));

        pairs.put(27, new Market(27, "TRC", "BTC", "TerraCoin"));

        pairs.put(203, new Market(203, "UNB", "BTC", "UnbreakableCoin"));

        pairs.put(133, new Market(133, "UNO", "BTC", "Unobtanium"));

        pairs.put(247, new Market(247, "URO", "BTC", "URO"));

        pairs.put(163, new Market(163, "UTC", "BTC", "UltraCoin"));

        pairs.put(261, new Market(261, "VIA", "BTC", "ViaCoin"));

        pairs.put(254, new Market(254, "VOOT", "BTC", "VootCoin"));

        pairs.put(209, new Market(209, "VRC", "BTC", "VeriCoin"));

        pairs.put(151, new Market(151, "VTC", "BTC", "VertCoin"));

        pairs.put(195, new Market(195, "WC", "BTC", "WhiteCoin"));

        pairs.put(14, new Market(14, "WDC", "BTC", "WorldCoin"));

        pairs.put(210, new Market(210, "XC", "BTC", "X11Coin"));

        pairs.put(280, new Market(280, "XCR", "BTC", "Crypti"));

        pairs.put(115, new Market(115, "XJO", "BTC", "JouleCoin"));

        pairs.put(208, new Market(208, "XLB", "BTC", "LibertyCoin"));

        pairs.put(63, new Market(63, "XPM", "BTC", "PrimeCoin"));

        pairs.put(265, new Market(265, "XXX", "BTC", "XXXCoin"));

        pairs.put(11, new Market(11, "YAC", "BTC", "YaCoin"));

        pairs.put(73, new Market(73, "YBC", "BTC", "YBCoin"));

        pairs.put(140, new Market(140, "ZCC", "BTC", "ZcCoin"));

        pairs.put(170, new Market(170, "ZED", "BTC", "ZedCoin"));

        pairs.put(85, new Market(85, "ZET", "BTC", "ZetaCoin"));


    }

    public static Market getMarket(Integer id) {

        return pairs.get(id);

    }

    public static Market getMarket(String primary, String secondary) {

        Market tempMarket = new Market();

        for (Market m : getAllMarkets()) {
            if ((m.getPrimarycode().toUpperCase().equals(primary.toUpperCase())) && (m.getSecondarycode().toUpperCase().equals(secondary.toUpperCase()))) {
                tempMarket = m;
            }
        }

        return getMarket(tempMarket.getMarketid());

    }

    public static ArrayList<Market> getAllMarkets() {

        ArrayList<Market> temp;
        temp = new ArrayList<Market>(pairs.values());
        Collections.sort(temp);
        //Log.i("PAIRS", "sorted arraylist temp: " + temp);
        return temp;

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

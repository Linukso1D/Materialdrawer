package com.dcodepages.material.MetroNaviF;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import fr.ganfra.materialspinner.MaterialSpinner;

import static com.dcodepages.material.MetroNaviF.ScreenMetr.getInstance;

/**
 * Created by maxxl2012 on 11/11/2015.
 */
public class MainActivity extends ActionBarActivity implements DrawerCallbacks {

private MaterialSpinner spinner1,spinner2;
    public String
            HVokKom,
            VokKom,
            HMetKom,
            HMetVok,
            MetVok,
            HLurgVok,
            LurgVok,
            HLurgKom,
            LurgKom,
            ZavVok,
            ZavKom,
            HZavVok,
            HZavKom,
            PrSvKom,
            HPrSvKom,
            PrSvVok,
            HPrSvVok,
            HKomVok,
            KomVok;
    public String NaprFirst, NaprTwo;
    public int modiX, modiY, modiX1, modiX2, modiY1, modiY2;
    ListView lvMain;
    TextView ShowInfText;
    TextView ShowInfmapText;
    CheckBox FirstSwitch;
    // хранения расписание в двух масивах
    int ShedTime[] = new int[1700];
    String ShedName[] = new String[1700];
    //переменніе вівода в текстовое поле
    int Out1, Out2;
    Calendar newCal = new GregorianCalendar();
    SimpleDateFormat Time = null;
    //H - hollyday
    //Vok - Vokzal
    //Met -Metrostroiteli
    //Lurg - metalurgovskaya
    //Zav - zavodskaya
    //PrSv - ProspektSvobotu
    //Kom - komunarovskaya
    Date currentDate = null;
    int parsetime;
    private Toolbar mToolbar;
    private Nav_DrawerFragment mNavigationNavDrawerFragment;
    private int SizeText;

    private List <String> Raspto = new ArrayList<String>();
    String keyFirst,keyTwo;


    //TODO 30.10.2015
    ArrayList OstToVokzal, OstToKomunar;

    //преабразование времении в норм вид
    public static String ConvertTime(int t) {
        if (t != 0) {
            String ret;
            ret = Integer.toString(t);
            if (ret.length() > 3) {
                ret = ret.substring(0, 2) + ":" + ret.substring(2, ret.length());

            } else {
                ret = ret.substring(0, 1) + ":" + ret.substring(1, ret.length());

            }
            return ret;
        }
        return "00:00";
    }

    public static int ConvertOfTime(String str) {
        String ret;
        ret = str;
        ret = ret.substring(0, 2) + ret.substring(3, ret.length());
        return Integer.parseInt(ret);
    }

    // возвращает тру если рабочий день недели
    public static boolean WorkedDay() {
        Calendar newCal = new GregorianCalendar();
        final int day = newCal.get(Calendar.DAY_OF_WEEK);
        if (day == 1 || day == 7) return false;
        else return true;
    }
//добавление записи в масивы

    //Расчет времени
    public int Time_Minus(int a, int b) { // a - b
        int ha = a / 100, hb = b / 100;
        int ma = a % 100, mb = b % 100;

        if (mb > ma) { //1305 - 1240 => 1265 - 1240
            ma += 60;
            --ha;
        }

        ma -= mb;
        ha -= hb;

        return ha * 100 + ma;


    }

    void ShedulePut(String str, int num) {
        for (int i = 0; i < 1700; i++) {
            if (ShedTime[i] == 0) {
                ShedTime[i] = num;
                ShedName[i] = str;
                break;
            }
        }


    }

    //Инициализация расписания
    void init() {

        for (int i = 0; i < 1700; i++) {
            ShedTime[i] = 0;
            ShedName[i] = "";
        }


        // вокзальная - комунар выходной день
        ShedulePut("HVokKom", 540);
        ShedulePut("HVokKom", 558);
        ShedulePut("HVokKom", 614);
        ShedulePut("HVokKom", 630);
        ShedulePut("HVokKom", 646);
        ShedulePut("HVokKom", 702);
        ShedulePut("HVokKom", 718);
        ShedulePut("HVokKom", 734);
        ShedulePut("HVokKom", 750);
        ShedulePut("HVokKom", 806);
        ShedulePut("HVokKom", 822);
        ShedulePut("HVokKom", 838);
        ShedulePut("HVokKom", 854);
        ShedulePut("HVokKom", 910);
        ShedulePut("HVokKom", 926);
        ShedulePut("HVokKom", 942);
        ShedulePut("HVokKom", 958);
        ShedulePut("HVokKom", 1014);
        ShedulePut("HVokKom", 1030);
        ShedulePut("HVokKom", 1046);
        ShedulePut("HVokKom", 1102);
        ShedulePut("HVokKom", 1118);
        ShedulePut("HVokKom", 1134);
        ShedulePut("HVokKom", 1150);
        ShedulePut("HVokKom", 1206);
        ShedulePut("HVokKom", 1232);
        ShedulePut("HVokKom", 1238);
        ShedulePut("HVokKom", 1254);
        ShedulePut("HVokKom", 1310);
        ShedulePut("HVokKom", 1326);
        ShedulePut("HVokKom", 1342);
        ShedulePut("HVokKom", 1358);
        ShedulePut("HVokKom", 1414);
        ShedulePut("HVokKom", 1430);
        ShedulePut("HVokKom", 1446);
        ShedulePut("HVokKom", 1502);
        ShedulePut("HVokKom", 1518);
        ShedulePut("HVokKom", 1535);
        ShedulePut("HVokKom", 1551);
        ShedulePut("HVokKom", 1608);
        ShedulePut("HVokKom", 1624);
        ShedulePut("HVokKom", 1641);
        ShedulePut("HVokKom", 1657);
        ShedulePut("HVokKom", 1713);
        ShedulePut("HVokKom", 1729);
        ShedulePut("HVokKom", 1745);
        ShedulePut("HVokKom", 1801);
        ShedulePut("HVokKom", 1817);
        ShedulePut("HVokKom", 1833);
        ShedulePut("HVokKom", 1849);
        ShedulePut("HVokKom", 1905);
        ShedulePut("HVokKom", 1921);
        ShedulePut("HVokKom", 1937);
        ShedulePut("HVokKom", 1953);
        ShedulePut("HVokKom", 2009);
        ShedulePut("HVokKom", 2025);
        ShedulePut("HVokKom", 2041);
        ShedulePut("HVokKom", 2057);
        ShedulePut("HVokKom", 2113);
        ShedulePut("HVokKom", 2129);
        ShedulePut("HVokKom", 2145);
        ShedulePut("HVokKom", 2201);
        ShedulePut("HVokKom", 2217);
        ShedulePut("HVokKom", 2233);
        ShedulePut("HVokKom", 2249);
        ShedulePut("HVokKom", 2305);
        //вокзальная - комунар рабочий день
        ShedulePut("VokKom", 540);
        ShedulePut("VokKom", 554);
        ShedulePut("VokKom", 606);
        ShedulePut("VokKom", 618);
        ShedulePut("VokKom", 629);
        ShedulePut("VokKom", 640);
        ShedulePut("VokKom", 651);
        ShedulePut("VokKom", 659);
        ShedulePut("VokKom", 705);
        ShedulePut("VokKom", 711);
        ShedulePut("VokKom", 717);
        ShedulePut("VokKom", 724);
        ShedulePut("VokKom", 731);
        ShedulePut("VokKom", 738);
        ShedulePut("VokKom", 745);
        ShedulePut("VokKom", 752);
        ShedulePut("VokKom", 800);
        ShedulePut("VokKom", 808);
        ShedulePut("VokKom", 817);
        ShedulePut("VokKom", 825);
        ShedulePut("VokKom", 832);
        ShedulePut("VokKom", 840);
        ShedulePut("VokKom", 849);
        ShedulePut("VokKom", 857);
        ShedulePut("VokKom", 907);
        ShedulePut("VokKom", 918);
        ShedulePut("VokKom", 929);
        ShedulePut("VokKom", 940);
        ShedulePut("VokKom", 951);
        ShedulePut("VokKom", 1002);
        ShedulePut("VokKom", 1013);
        ShedulePut("VokKom", 1029);
        ShedulePut("VokKom", 1045);
        ShedulePut("VokKom", 1045);
        ShedulePut("VokKom", 1101);
        ShedulePut("VokKom", 1117);
        ShedulePut("VokKom", 1133);
        ShedulePut("VokKom", 1144);
        ShedulePut("VokKom", 1205);
        ShedulePut("VokKom", 1221);
        ShedulePut("VokKom", 1237);
        ShedulePut("VokKom", 1253);
        ShedulePut("VokKom", 1309);
        ShedulePut("VokKom", 1325);
        ShedulePut("VokKom", 1341);
        ShedulePut("VokKom", 1357);
        ShedulePut("VokKom", 1413);
        ShedulePut("VokKom", 1429);
        ShedulePut("VokKom", 1446);
        ShedulePut("VokKom", 1459);
        ShedulePut("VokKom", 1510);
        ShedulePut("VokKom", 1521);
        ShedulePut("VokKom", 1532);
        ShedulePut("VokKom", 1543);
        ShedulePut("VokKom", 1554);
        ShedulePut("VokKom", 1605);
        ShedulePut("VokKom", 1616);
        ShedulePut("VokKom", 1627);
        ShedulePut("VokKom", 1638);
        ShedulePut("VokKom", 1649);
        ShedulePut("VokKom", 1700);
        ShedulePut("VokKom", 1711);
        ShedulePut("VokKom", 1722);
        ShedulePut("VokKom", 1733);
        ShedulePut("VokKom", 1744);
        ShedulePut("VokKom", 1755);
        ShedulePut("VokKom", 1806);
        ShedulePut("VokKom", 1817);
        ShedulePut("VokKom", 1828);
        ShedulePut("VokKom", 1839);
        ShedulePut("VokKom", 1849);
        ShedulePut("VokKom", 1901);
        ShedulePut("VokKom", 1912);
        ShedulePut("VokKom", 1921);
        ShedulePut("VokKom", 1937);
        ShedulePut("VokKom", 1953);
        ShedulePut("VokKom", 2009);
        ShedulePut("VokKom", 2025);
        ShedulePut("VokKom", 2041);
        ShedulePut("VokKom", 2057);
        ShedulePut("VokKom", 2113);
        ShedulePut("VokKom", 2129);
        ShedulePut("VokKom", 2145);
        ShedulePut("VokKom", 2201);
        ShedulePut("VokKom", 2217);
        ShedulePut("VokKom", 2233);
        ShedulePut("VokKom", 2249);
        ShedulePut("VokKom", 2305);
        // метростроители - комунаровская
        ShedulePut("HMetKom", 543);
        ShedulePut("HMetKom", 600);
        ShedulePut("HMetKom", 616);
        ShedulePut("HMetKom", 632);
        ShedulePut("HMetKom", 648);
        ShedulePut("HMetKom", 704);
        ShedulePut("HMetKom", 720);
        ShedulePut("HMetKom", 736);
        ShedulePut("HMetKom", 752);
        ShedulePut("HMetKom", 808);
        ShedulePut("HMetKom", 824);
        ShedulePut("HMetKom", 840);
        ShedulePut("HMetKom", 856);
        ShedulePut("HMetKom", 912);
        ShedulePut("HMetKom", 928);
        ShedulePut("HMetKom", 944);
        ShedulePut("HMetKom", 1000);
        ShedulePut("HMetKom", 1016);
        ShedulePut("HMetKom", 1032);
        ShedulePut("HMetKom", 1048);
        ShedulePut("HMetKom", 1104);
        ShedulePut("HMetKom", 1120);
        ShedulePut("HMetKom", 1136);
        ShedulePut("HMetKom", 1152);
        ShedulePut("HMetKom", 1208);
        ShedulePut("HMetKom", 1224);
        ShedulePut("HMetKom", 1240);
        ShedulePut("HMetKom", 1256);
        ShedulePut("HMetKom", 1312);
        ShedulePut("HMetKom", 1328);
        ShedulePut("HMetKom", 1344);
        ShedulePut("HMetKom", 1400);
        ShedulePut("HMetKom", 1416);
        ShedulePut("HMetKom", 1432);
        ShedulePut("HMetKom", 1448);
        ShedulePut("HMetKom", 1504);
        ShedulePut("HMetKom", 1521);
        ShedulePut("HMetKom", 1537);
        ShedulePut("HMetKom", 1554);
        ShedulePut("HMetKom", 1610);
        ShedulePut("HMetKom", 1627);
        ShedulePut("HMetKom", 1643);
        ShedulePut("HMetKom", 1659);
        ShedulePut("HMetKom", 1715);
        ShedulePut("HMetKom", 1731);
        ShedulePut("HMetKom", 1747);
        ShedulePut("HMetKom", 1803);
        ShedulePut("HMetKom", 1819);
        ShedulePut("HMetKom", 1835);
        ShedulePut("HMetKom", 1851);
        ShedulePut("HMetKom", 1907);
        ShedulePut("HMetKom", 1923);
        ShedulePut("HMetKom", 1939);
        ShedulePut("HMetKom", 1955);
        ShedulePut("HMetKom", 2011);
        ShedulePut("HMetKom", 2027);
        ShedulePut("HMetKom", 2043);
        ShedulePut("HMetKom", 2059);
        ShedulePut("HMetKom", 2115);
        ShedulePut("HMetKom", 2131);
        ShedulePut("HMetKom", 2147);
        ShedulePut("HMetKom", 2203);
        ShedulePut("HMetKom", 2219);
        ShedulePut("HMetKom", 2230);
        ShedulePut("HMetKom", 2251);
        ShedulePut("HMetKom", 2307);
        //метростроители - вокзальная по выходным
        ShedulePut("HMetVok", 551);
        ShedulePut("HMetVok", 607);
        ShedulePut("HMetVok", 623);
        ShedulePut("HMetVok", 639);
        ShedulePut("HMetVok", 655);
        ShedulePut("HMetVok", 711);
        ShedulePut("HMetVok", 727);
        ShedulePut("HMetVok", 743);
        ShedulePut("HMetVok", 759);
        ShedulePut("HMetVok", 815);
        ShedulePut("HMetVok", 831);
        ShedulePut("HMetVok", 847);
        ShedulePut("HMetVok", 903);
        ShedulePut("HMetVok", 919);
        ShedulePut("HMetVok", 935);
        ShedulePut("HMetVok", 951);
        ShedulePut("HMetVok", 1007);
        ShedulePut("HMetVok", 1023);
        ShedulePut("HMetVok", 1039);
        ShedulePut("HMetVok", 1055);
        ShedulePut("HMetVok", 1111);
        ShedulePut("HMetVok", 1127);
        ShedulePut("HMetVok", 1143);
        ShedulePut("HMetVok", 1159);
        ShedulePut("HMetVok", 1215);
        ShedulePut("HMetVok", 1331);
        ShedulePut("HMetVok", 1247);
        ShedulePut("HMetVok", 1303);
        ShedulePut("HMetVok", 1319);
        ShedulePut("HMetVok", 1335);
        ShedulePut("HMetVok", 1351);
        ShedulePut("HMetVok", 1407);
        ShedulePut("HMetVok", 1423);
        ShedulePut("HMetVok", 1439);
        ShedulePut("HMetVok", 1455);
        ShedulePut("HMetVok", 1511);
        ShedulePut("HMetVok", 1528);
        ShedulePut("HMetVok", 1544);
        ShedulePut("HMetVok", 1601);
        ShedulePut("HMetVok", 1617);
        ShedulePut("HMetVok", 1634);
        ShedulePut("HMetVok", 1650);
        ShedulePut("HMetVok", 1706);
        ShedulePut("HMetVok", 1722);
        ShedulePut("HMetVok", 1738);
        ShedulePut("HMetVok", 1754);
        ShedulePut("HMetVok", 1810);
        ShedulePut("HMetVok", 1826);
        ShedulePut("HMetVok", 1842);
        ShedulePut("HMetVok", 1858);
        ShedulePut("HMetVok", 1914);
        ShedulePut("HMetVok", 1930);
        ShedulePut("HMetVok", 1946);
        ShedulePut("HMetVok", 2002);
        ShedulePut("HMetVok", 2018);
        ShedulePut("HMetVok", 2034);
        ShedulePut("HMetVok", 2050);
        ShedulePut("HMetVok", 2106);
        ShedulePut("HMetVok", 2122);
        ShedulePut("HMetVok", 2138);
        ShedulePut("HMetVok", 2154);
        ShedulePut("HMetVok", 2210);
        ShedulePut("HMetVok", 2226);
        ShedulePut("HMetVok", 2242);
        ShedulePut("HMetVok", 2258);
        ShedulePut("HMetVok", 2314);

        //Метростроителей -> Коммунаровская по рабочим дням:
        ShedulePut("MetKom", 543);
        ShedulePut("MetKom", 556);
        ShedulePut("MetKom", 608);
        ShedulePut("MetKom", 620);
        ShedulePut("MetKom", 631);
        ShedulePut("MetKom", 642);
        ShedulePut("MetKom", 653);
        ShedulePut("MetKom", 701);
        ShedulePut("MetKom", 707);
        ShedulePut("MetKom", 713);
        ShedulePut("MetKom", 719);
        ShedulePut("MetKom", 726);
        ShedulePut("MetKom", 733);
        ShedulePut("MetKom", 740);
        ShedulePut("MetKom", 747);
        ShedulePut("MetKom", 754);
        ShedulePut("MetKom", 802);
        ShedulePut("MetKom", 810);
        ShedulePut("MetKom", 819);
        ShedulePut("MetKom", 828);
        ShedulePut("MetKom", 834);
        ShedulePut("MetKom", 842);
        ShedulePut("MetKom", 851);
        ShedulePut("MetKom", 859);
        ShedulePut("MetKom", 909);
        ShedulePut("MetKom", 920);
        ShedulePut("MetKom", 931);
        ShedulePut("MetKom", 942);
        ShedulePut("MetKom", 953);
        ShedulePut("MetKom", 1004);
        ShedulePut("MetKom", 1015);
        ShedulePut("MetKom", 1031);
        ShedulePut("MetKom", 1046);
        ShedulePut("MetKom", 1103);
        ShedulePut("MetKom", 1119);
        ShedulePut("MetKom", 1135);
        ShedulePut("MetKom", 1151);
        ShedulePut("MetKom", 1207);
        ShedulePut("MetKom", 1223);
        ShedulePut("MetKom", 1239);
        ShedulePut("MetKom", 1255);
        ShedulePut("MetKom", 1311);
        ShedulePut("MetKom", 1327);
        ShedulePut("MetKom", 1343);
        ShedulePut("MetKom", 1359);
        ShedulePut("MetKom", 1415);
        ShedulePut("MetKom", 1431);
        ShedulePut("MetKom", 1448);
        ShedulePut("MetKom", 1501);
        ShedulePut("MetKom", 1512);
        ShedulePut("MetKom", 1523);
        ShedulePut("MetKom", 1534);
        ShedulePut("MetKom", 1545);
        ShedulePut("MetKom", 1556);
        ShedulePut("MetKom", 1607);
        ShedulePut("MetKom", 1618);
        ShedulePut("MetKom", 1629);
        ShedulePut("MetKom", 1640);
        ShedulePut("MetKom", 1651);
        ShedulePut("MetKom", 1702);
        ShedulePut("MetKom", 1713);
        ShedulePut("MetKom", 1724);
        ShedulePut("MetKom", 1735);
        ShedulePut("MetKom", 1746);
        ShedulePut("MetKom", 1757);
        ShedulePut("MetKom", 1808);
        ShedulePut("MetKom", 1819);
        ShedulePut("MetKom", 1830);
        ShedulePut("MetKom", 1841);
        ShedulePut("MetKom", 1851);
        ShedulePut("MetKom", 1903);
        ShedulePut("MetKom", 1914);
        ShedulePut("MetKom", 1923);
        ShedulePut("MetKom", 1939);
        ShedulePut("MetKom", 1955);
        ShedulePut("MetKom", 2011);
        ShedulePut("MetKom", 2027);
        ShedulePut("MetKom", 2043);
        ShedulePut("MetKom", 2059);
        ShedulePut("MetKom", 2115);
        ShedulePut("MetKom", 2131);
        ShedulePut("MetKom", 2147);
        ShedulePut("MetKom", 2203);
        ShedulePut("MetKom", 2219);
        ShedulePut("MetKom", 2235);
        ShedulePut("MetKom", 2251);
        ShedulePut("MetKom", 2307);

        //Метростроителей -> Вокзальная по рабочим дням:
        ShedulePut("MetVok", 547);
        ShedulePut("MetVok", 559);
        ShedulePut("MetVok", 611);
        ShedulePut("MetVok", 622);
        ShedulePut("MetVok", 633);
        ShedulePut("MetVok", 644);
        ShedulePut("MetVok", 652);
        ShedulePut("MetVok", 659);
        ShedulePut("MetVok", 704);
        ShedulePut("MetVok", 710);
        ShedulePut("MetVok", 717);
        ShedulePut("MetVok", 724);
        ShedulePut("MetVok", 731);
        ShedulePut("MetVok", 738);
        ShedulePut("MetVok", 745);
        ShedulePut("MetVok", 753);
        ShedulePut("MetVok", 801);
        ShedulePut("MetVok", 810);
        ShedulePut("MetVok", 818);
        ShedulePut("MetVok", 825);
        ShedulePut("MetVok", 833);
        ShedulePut("MetVok", 842);
        ShedulePut("MetVok", 850);
        ShedulePut("MetVok", 900);
        ShedulePut("MetVok", 911);
        ShedulePut("MetVok", 922);
        ShedulePut("MetVok", 933);
        ShedulePut("MetVok", 943);
        ShedulePut("MetVok", 954);
        ShedulePut("MetVok", 1006);
        ShedulePut("MetVok", 1021);
        ShedulePut("MetVok", 1038);
        ShedulePut("MetVok", 1054);
        ShedulePut("MetVok", 1110);
        ShedulePut("MetVok", 1126);
        ShedulePut("MetVok", 1142);
        ShedulePut("MetVok", 1158);
        ShedulePut("MetVok", 1214);
        ShedulePut("MetVok", 1230);
        ShedulePut("MetVok", 1246);
        ShedulePut("MetVok", 1302);
        ShedulePut("MetVok", 1318);
        ShedulePut("MetVok", 1334);
        ShedulePut("MetVok", 1350);
        ShedulePut("MetVok", 1406);
        ShedulePut("MetVok", 1422);
        ShedulePut("MetVok", 1438);
        ShedulePut("MetVok", 1452);
        ShedulePut("MetVok", 1503);
        ShedulePut("MetVok", 1514);
        ShedulePut("MetVok", 1525);
        ShedulePut("MetVok", 1536);
        ShedulePut("MetVok", 1547);
        ShedulePut("MetVok", 1558);
        ShedulePut("MetVok", 1609);
        ShedulePut("MetVok", 1620);
        ShedulePut("MetVok", 1631);
        ShedulePut("MetVok", 1642);
        ShedulePut("MetVok", 1653);
        ShedulePut("MetVok", 1704);
        ShedulePut("MetVok", 1715);
        ShedulePut("MetVok", 1726);
        ShedulePut("MetVok", 1737);
        ShedulePut("MetVok", 1748);
        ShedulePut("MetVok", 1759);
        ShedulePut("MetVok", 1810);
        ShedulePut("MetVok", 1821);
        ShedulePut("MetVok", 1832);
        ShedulePut("MetVok", 1842);
        ShedulePut("MetVok", 1854);
        ShedulePut("MetVok", 1905);
        ShedulePut("MetVok", 1914);
        ShedulePut("MetVok", 1930);
        ShedulePut("MetVok", 1946);
        ShedulePut("MetVok", 2002);
        ShedulePut("MetVok", 2018);
        ShedulePut("MetVok", 2034);
        ShedulePut("MetVok", 2050);
        ShedulePut("MetVok", 2106);
        ShedulePut("MetVok", 2122);
        ShedulePut("MetVok", 2138);
        ShedulePut("MetVok", 2154);
        ShedulePut("MetVok", 2210);
        ShedulePut("MetVok", 2226);
        ShedulePut("MetVok", 2242);
        ShedulePut("MetVok", 2258);
        ShedulePut("MetVok", 2314);
//Металлургов -> Вокзальная по выходным дням:
        ShedulePut("HLurgVok", 548);
        ShedulePut("HLurgVok", 605);
        ShedulePut("HLurgVok", 621);
        ShedulePut("HLurgVok", 637);
        ShedulePut("HLurgVok", 658);
        ShedulePut("HLurgVok", 709);
        ShedulePut("HLurgVok", 725);
        ShedulePut("HLurgVok", 741);
        ShedulePut("HLurgVok", 757);
        ShedulePut("HLurgVok", 813);
        ShedulePut("HLurgVok", 829);
        ShedulePut("HLurgVok", 845);
        ShedulePut("HLurgVok", 901);
        ShedulePut("HLurgVok", 917);
        ShedulePut("HLurgVok", 933);
        ShedulePut("HLurgVok", 949);
        ShedulePut("HLurgVok", 1005);
        ShedulePut("HLurgVok", 1021);
        ShedulePut("HLurgVok", 1037);
        ShedulePut("HLurgVok", 1053);
        ShedulePut("HLurgVok", 1109);
        ShedulePut("HLurgVok", 1125);
        ShedulePut("HLurgVok", 1141);
        ShedulePut("HLurgVok", 1157);
        ShedulePut("HLurgVok", 1213);
        ShedulePut("HLurgVok", 1229);
        ShedulePut("HLurgVok", 1245);
        ShedulePut("HLurgVok", 1301);
        ShedulePut("HLurgVok", 1317);
        ShedulePut("HLurgVok", 1333);
        ShedulePut("HLurgVok", 1349);
        ShedulePut("HLurgVok", 1405);
        ShedulePut("HLurgVok", 1421);
        ShedulePut("HLurgVok", 1437);
        ShedulePut("HLurgVok", 1453);
        ShedulePut("HLurgVok", 1509);
        ShedulePut("HLurgVok", 1526);
        ShedulePut("HLurgVok", 1542);
        ShedulePut("HLurgVok", 1558);
        ShedulePut("HLurgVok", 1615);
        ShedulePut("HLurgVok", 1632);
        ShedulePut("HLurgVok", 1648);
        ShedulePut("HLurgVok", 1704);
        ShedulePut("HLurgVok", 1720);
        ShedulePut("HLurgVok", 1732);
        ShedulePut("HLurgVok", 1752);
        ShedulePut("HLurgVok", 1808);
        ShedulePut("HLurgVok", 1824);
        ShedulePut("HLurgVok", 1840);
        ShedulePut("HLurgVok", 1856);
        ShedulePut("HLurgVok", 1912);
        ShedulePut("HLurgVok", 1928);
        ShedulePut("HLurgVok", 1944);
        ShedulePut("HLurgVok", 2000);
        ShedulePut("HLurgVok", 2016);
        ShedulePut("HLurgVok", 2032);
        ShedulePut("HLurgVok", 2048);
        ShedulePut("HLurgVok", 2104);
        ShedulePut("HLurgVok", 2120);
        ShedulePut("HLurgVok", 2136);
        ShedulePut("HLurgVok", 2152);
        ShedulePut("HLurgVok", 2208);
        ShedulePut("HLurgVok", 2224);
        ShedulePut("HLurgVok", 2240);
        ShedulePut("HLurgVok", 2256);
        ShedulePut("HLurgVok", 2312);
        //Металлургов -> Вокзальная по рабочим дням:
        ShedulePut("LurgVok", 544);
        ShedulePut("LurgVok", 556);
        ShedulePut("LurgVok", 604);
        ShedulePut("LurgVok", 619);
        ShedulePut("LurgVok", 630);
        ShedulePut("LurgVok", 641);
        ShedulePut("LurgVok", 650);
        ShedulePut("LurgVok", 656);
        ShedulePut("LurgVok", 701);
        ShedulePut("LurgVok", 707);
        ShedulePut("LurgVok", 714);
        ShedulePut("LurgVok", 721);
        ShedulePut("LurgVok", 728);
        ShedulePut("LurgVok", 735);
        ShedulePut("LurgVok", 742);
        ShedulePut("LurgVok", 750);
        ShedulePut("LurgVok", 758);
        ShedulePut("LurgVok", 807);
        ShedulePut("LurgVok", 815);
        ShedulePut("LurgVok", 822);
        ShedulePut("LurgVok", 830);
        ShedulePut("LurgVok", 839);
        ShedulePut("LurgVok", 847);
        ShedulePut("LurgVok", 857);
        ShedulePut("LurgVok", 908);
        ShedulePut("LurgVok", 919);
        ShedulePut("LurgVok", 930);
        ShedulePut("LurgVok", 941);
        ShedulePut("LurgVok", 952);
        ShedulePut("LurgVok", 1003);
        ShedulePut("LurgVok", 1019);
        ShedulePut("LurgVok", 1035);
        ShedulePut("LurgVok", 1051);
        ShedulePut("LurgVok", 1107);
        ShedulePut("LurgVok", 1123);
        ShedulePut("LurgVok", 1155);
        ShedulePut("LurgVok", 1212);
        ShedulePut("LurgVok", 1227);
        ShedulePut("LurgVok", 1243);
        ShedulePut("LurgVok", 1259);
        ShedulePut("LurgVok", 1315);
        ShedulePut("LurgVok", 1330);
        ShedulePut("LurgVok", 1346);
        ShedulePut("LurgVok", 1403);
        ShedulePut("LurgVok", 1419);
        ShedulePut("LurgVok", 1419);
        ShedulePut("LurgVok", 1435);
        ShedulePut("LurgVok", 1449);
        ShedulePut("LurgVok", 1500);
        ShedulePut("LurgVok", 1511);
        ShedulePut("LurgVok", 1522);
        ShedulePut("LurgVok", 1533);
        ShedulePut("LurgVok", 1544);
        ShedulePut("LurgVok", 1556);
        ShedulePut("LurgVok", 1606);
        ShedulePut("LurgVok", 1617);
        ShedulePut("LurgVok", 1628);
        ShedulePut("LurgVok", 1639);
        ShedulePut("LurgVok", 1650);
        ShedulePut("LurgVok", 1701);
        ShedulePut("LurgVok", 1712);
        ShedulePut("LurgVok", 1723);
        ShedulePut("LurgVok", 1734);
        ShedulePut("LurgVok", 1745);
        ShedulePut("LurgVok", 1756);
        ShedulePut("LurgVok", 1807);
        ShedulePut("LurgVok", 1818);
        ShedulePut("LurgVok", 1829);
        ShedulePut("LurgVok", 1839);
        ShedulePut("LurgVok", 1851);
        ShedulePut("LurgVok", 1902);
        ShedulePut("LurgVok", 1911);
        ShedulePut("LurgVok", 1927);
        ShedulePut("LurgVok", 1943);
        ShedulePut("LurgVok", 1959);
        ShedulePut("LurgVok", 2015);
        ShedulePut("LurgVok", 2031);
        ShedulePut("LurgVok", 2047);
        ShedulePut("LurgVok", 2103);
        ShedulePut("LurgVok", 2119);
        ShedulePut("LurgVok", 2135);
        ShedulePut("LurgVok", 2151);
        ShedulePut("LurgVok", 2207);
        ShedulePut("LurgVok", 2223);
        ShedulePut("LurgVok", 2239);
        ShedulePut("LurgVok", 2255);
        //Металлургов -> Коммунаровская по выходным дням:
        ShedulePut("HLurgKom", 546);
        ShedulePut("HLurgKom", 603);
        ShedulePut("HLurgKom", 619);
        ShedulePut("HLurgKom", 635);
        ShedulePut("HLurgKom", 651);
        ShedulePut("HLurgKom", 707);
        ShedulePut("HLurgKom", 723);
        ShedulePut("HLurgKom", 739);
        ShedulePut("HLurgKom", 755);
        ShedulePut("HLurgKom", 811);
        ShedulePut("HLurgKom", 827);
        ShedulePut("HLurgKom", 843);
        ShedulePut("HLurgKom", 859);
        ShedulePut("HLurgKom", 915);
        ShedulePut("HLurgKom", 931);
        ShedulePut("HLurgKom", 947);
        ShedulePut("HLurgKom", 1003);
        ShedulePut("HLurgKom", 1019);
        ShedulePut("HLurgKom", 1035);
        ShedulePut("HLurgKom", 1051);
        ShedulePut("HLurgKom", 1107);
        ShedulePut("HLurgKom", 1123);
        ShedulePut("HLurgKom", 1139);
        ShedulePut("HLurgKom", 1155);
        ShedulePut("HLurgKom", 1211);
        ShedulePut("HLurgKom", 1227);
        ShedulePut("HLurgKom", 1243);
        ShedulePut("HLurgKom", 1259);
        ShedulePut("HLurgKom", 1315);
        ShedulePut("HLurgKom", 1331);
        ShedulePut("HLurgKom", 1347);
        ShedulePut("HLurgKom", 1403);
        ShedulePut("HLurgKom", 1419);
        ShedulePut("HLurgKom", 1435);
        ShedulePut("HLurgKom", 1451);
        ShedulePut("HLurgKom", 1507);
        ShedulePut("HLurgKom", 1523);
        ShedulePut("HLurgKom", 1540);
        ShedulePut("HLurgKom", 1556);
        ShedulePut("HLurgKom", 1613);
        ShedulePut("HLurgKom", 1629);
        ShedulePut("HLurgKom", 1646);
        ShedulePut("HLurgKom", 1702);
        ShedulePut("HLurgKom", 1718);
        ShedulePut("HLurgKom", 1734);
        ShedulePut("HLurgKom", 1750);
        ShedulePut("HLurgKom", 1806);
        ShedulePut("HLurgKom", 1822);
        ShedulePut("HLurgKom", 1838);
        ShedulePut("HLurgKom", 1854);
        ShedulePut("HLurgKom", 1910);
        ShedulePut("HLurgKom", 1926);
        ShedulePut("HLurgKom", 1942);
        ShedulePut("HLurgKom", 1958);
        ShedulePut("HLurgKom", 2014);
        ShedulePut("HLurgKom", 2030);
        ShedulePut("HLurgKom", 2046);
        ShedulePut("HLurgKom", 2102);
        ShedulePut("HLurgKom", 2118);
        ShedulePut("HLurgKom", 2134);
        ShedulePut("HLurgKom", 2150);
        ShedulePut("HLurgKom", 2206);
        ShedulePut("HLurgKom", 2222);
        ShedulePut("HLurgKom", 2238);
        ShedulePut("HLurgKom", 2254);
        ShedulePut("HLurgKom", 2310);
        //Металлургов -> Коммунаровская по рабочим дням:
        ShedulePut("LurgKom", 546);
        ShedulePut("LurgKom", 559);
        ShedulePut("LurgKom", 611);
        ShedulePut("LurgKom", 622);
        ShedulePut("LurgKom", 634);
        ShedulePut("LurgKom", 645);
        ShedulePut("LurgKom", 655);
        ShedulePut("LurgKom", 704);
        ShedulePut("LurgKom", 710);
        ShedulePut("LurgKom", 716);
        ShedulePut("LurgKom", 722);
        ShedulePut("LurgKom", 729);
        ShedulePut("LurgKom", 736);
        ShedulePut("LurgKom", 743);
        ShedulePut("LurgKom", 750);
        ShedulePut("LurgKom", 757);
        ShedulePut("LurgKom", 805);
        ShedulePut("LurgKom", 813);
        ShedulePut("LurgKom", 822);
        ShedulePut("LurgKom", 830);
        ShedulePut("LurgKom", 837);
        ShedulePut("LurgKom", 845);
        ShedulePut("LurgKom", 854);
        ShedulePut("LurgKom", 902);
        ShedulePut("LurgKom", 912);
        ShedulePut("LurgKom", 922);
        ShedulePut("LurgKom", 934);
        ShedulePut("LurgKom", 945);
        ShedulePut("LurgKom", 956);
        ShedulePut("LurgKom", 1007);
        ShedulePut("LurgKom", 1018);
        ShedulePut("LurgKom", 1034);
        ShedulePut("LurgKom", 1050);
        ShedulePut("LurgKom", 1106);
        ShedulePut("LurgKom", 1122);
        ShedulePut("LurgKom", 1138);
        ShedulePut("LurgKom", 1154);
        ShedulePut("LurgKom", 1210);
        ShedulePut("LurgKom", 1226);
        ShedulePut("LurgKom", 1242);
        ShedulePut("LurgKom", 1258);
        ShedulePut("LurgKom", 1314);
        ShedulePut("LurgKom", 1330);
        ShedulePut("LurgKom", 1346);
        ShedulePut("LurgKom", 1402);
        ShedulePut("LurgKom", 1418);
        ShedulePut("LurgKom", 1434);
        ShedulePut("LurgKom", 1451);
        ShedulePut("LurgKom", 1504);
        ShedulePut("LurgKom", 1515);
        ShedulePut("LurgKom", 1526);
        ShedulePut("LurgKom", 1537);
        ShedulePut("LurgKom", 1548);
        ShedulePut("LurgKom", 1559);
        ShedulePut("LurgKom", 1610);
        ShedulePut("LurgKom", 1621);
        ShedulePut("LurgKom", 1632);
        ShedulePut("LurgKom", 1643);
        ShedulePut("LurgKom", 1654);
        ShedulePut("LurgKom", 1705);
        ShedulePut("LurgKom", 1716);
        ShedulePut("LurgKom", 1738);
        ShedulePut("LurgKom", 1749);
        ShedulePut("LurgKom", 1800);
        ShedulePut("LurgKom", 1811);
        ShedulePut("LurgKom", 1822);
        ShedulePut("LurgKom", 1833);
        ShedulePut("LurgKom", 1844);
        ShedulePut("LurgKom", 1906);
        ShedulePut("LurgKom", 1917);
        ShedulePut("LurgKom", 1926);
        ShedulePut("LurgKom", 1942);
        ShedulePut("LurgKom", 1958);
        ShedulePut("LurgKom", 2014);
        ShedulePut("LurgKom", 2030);
        ShedulePut("LurgKom", 2046);
        ShedulePut("LurgKom", 2102);
        ShedulePut("LurgKom", 2118);
        ShedulePut("LurgKom", 2134);
        ShedulePut("LurgKom", 2150);
        ShedulePut("LurgKom", 2206);
        ShedulePut("LurgKom", 2222);
        ShedulePut("LurgKom", 2238);
        ShedulePut("LurgKom", 2254);
        ShedulePut("LurgKom", 2310);
        //Заводская -> Вокзальная по рабочим дням
        ShedulePut("ZavVok", 543);
        ShedulePut("ZavVok", 555);
        ShedulePut("ZavVok", 607);
        ShedulePut("ZavVok", 618);
        ShedulePut("ZavVok", 629);
        ShedulePut("ZavVok", 640);
        ShedulePut("ZavVok", 648);
        ShedulePut("ZavVok", 654);
        ShedulePut("ZavVok", 700);
        ShedulePut("ZavVok", 706);
        ShedulePut("ZavVok", 713);
        ShedulePut("ZavVok", 720);
        ShedulePut("ZavVok", 727);
        ShedulePut("ZavVok", 734);
        ShedulePut("ZavVok", 741);
        ShedulePut("ZavVok", 749);
        ShedulePut("ZavVok", 757);
        ShedulePut("ZavVok", 806);
        ShedulePut("ZavVok", 814);
        ShedulePut("ZavVok", 821);
        ShedulePut("ZavVok", 828);
        ShedulePut("ZavVok", 838);
        ShedulePut("ZavVok", 846);
        ShedulePut("ZavVok", 856);
        ShedulePut("ZavVok", 907);
        ShedulePut("ZavVok", 918);
        ShedulePut("ZavVok", 929);
        ShedulePut("ZavVok", 939);
        ShedulePut("ZavVok", 950);
        ShedulePut("ZavVok", 1001);
        ShedulePut("ZavVok", 1017);
        ShedulePut("ZavVok", 1034);
        ShedulePut("ZavVok", 1050);
        ShedulePut("ZavVok", 1106);
        ShedulePut("ZavVok", 1122);
        ShedulePut("ZavVok", 1138);
        ShedulePut("ZavVok", 1154);
        ShedulePut("ZavVok", 1210);
        ShedulePut("ZavVok", 1226);
        ShedulePut("ZavVok", 1242);
        ShedulePut("ZavVok", 1258);
        ShedulePut("ZavVok", 1314);
        ShedulePut("ZavVok", 1330);
        ShedulePut("ZavVok", 1346);
        ShedulePut("ZavVok", 1402);
        ShedulePut("ZavVok", 1418);
        ShedulePut("ZavVok", 1434);
        ShedulePut("ZavVok", 1448);
        ShedulePut("ZavVok", 1458);
        ShedulePut("ZavVok", 1509);
        ShedulePut("ZavVok", 1520);
        ShedulePut("ZavVok", 1615);
        ShedulePut("ZavVok", 1626);
        ShedulePut("ZavVok", 1638);
        ShedulePut("ZavVok", 1648);
        ShedulePut("ZavVok", 1659);
        ShedulePut("ZavVok", 1710);
        ShedulePut("ZavVok", 1721);
        ShedulePut("ZavVok", 1733);
        ShedulePut("ZavVok", 1743);
        ShedulePut("ZavVok", 1755);
        ShedulePut("ZavVok", 1806);
        ShedulePut("ZavVok", 1816);
        ShedulePut("ZavVok", 1827);
        ShedulePut("ZavVok", 1838);
        ShedulePut("ZavVok", 1850);
        ShedulePut("ZavVok", 1901);
        ShedulePut("ZavVok", 1910);
        ShedulePut("ZavVok", 1926);
        ShedulePut("ZavVok", 1942);
        ShedulePut("ZavVok", 1958);
        ShedulePut("ZavVok", 2014);
        ShedulePut("ZavVok", 2029);
        ShedulePut("ZavVok", 2046);
        ShedulePut("ZavVok", 2102);
        ShedulePut("ZavVok", 2118);
        ShedulePut("ZavVok", 2134);
        ShedulePut("ZavVok", 2150);
        ShedulePut("ZavVok", 2206);
        ShedulePut("ZavVok", 2224);
        ShedulePut("ZavVok", 2238);
        ShedulePut("ZavVok", 2254);
        ShedulePut("ZavVok", 2310);
        //Заводская -> Коммунаровская по рабочим дням
        ShedulePut("ZavKom", 548);
        ShedulePut("ZavKom", 601);
        ShedulePut("ZavKom", 613);
        ShedulePut("ZavKom", 625);
        ShedulePut("ZavKom", 636);
        ShedulePut("ZavKom", 647);
        ShedulePut("ZavKom", 658);
        ShedulePut("ZavKom", 706);
        ShedulePut("ZavKom", 712);
        ShedulePut("ZavKom", 718);
        ShedulePut("ZavKom", 724);
        ShedulePut("ZavKom", 731);
        ShedulePut("ZavKom", 738);
        ShedulePut("ZavKom", 745);
        ShedulePut("ZavKom", 752);
        ShedulePut("ZavKom", 759);
        ShedulePut("ZavKom", 807);
        ShedulePut("ZavKom", 815);
        ShedulePut("ZavKom", 824);
        ShedulePut("ZavKom", 832);
        ShedulePut("ZavKom", 839);
        ShedulePut("ZavKom", 847);
        ShedulePut("ZavKom", 856);
        ShedulePut("ZavKom", 904);
        ShedulePut("ZavKom", 914);
        ShedulePut("ZavKom", 924);
        ShedulePut("ZavKom", 936);
        ShedulePut("ZavKom", 947);
        ShedulePut("ZavKom", 957);
        ShedulePut("ZavKom", 1009);
        ShedulePut("ZavKom", 1020);
        ShedulePut("ZavKom", 1036);
        ShedulePut("ZavKom", 1052);
        ShedulePut("ZavKom", 1108);
        ShedulePut("ZavKom", 1124);
        ShedulePut("ZavKom", 1140);
        ShedulePut("ZavKom", 1156);
        ShedulePut("ZavKom", 1212);
        ShedulePut("ZavKom", 1228);
        ShedulePut("ZavKom", 1244);
        ShedulePut("ZavKom", 1300);
        ShedulePut("ZavKom", 1316);
        ShedulePut("ZavKom", 1332);
        ShedulePut("ZavKom", 1348);
        ShedulePut("ZavKom", 1403);
        ShedulePut("ZavKom", 1420);
        ShedulePut("ZavKom", 1436);
        ShedulePut("ZavKom", 1452);
        ShedulePut("ZavKom", 1506);
        ShedulePut("ZavKom", 1517);
        ShedulePut("ZavKom", 1528);
        ShedulePut("ZavKom", 1539);
        ShedulePut("ZavKom", 1550);
        ShedulePut("ZavKom", 1601);
        ShedulePut("ZavKom", 1612);
        ShedulePut("ZavKom", 1623);
        ShedulePut("ZavKom", 1634);
        ShedulePut("ZavKom", 1645);
        ShedulePut("ZavKom", 1656);
        ShedulePut("ZavKom", 1707);
        ShedulePut("ZavKom", 1718);
        ShedulePut("ZavKom", 1729);
        ShedulePut("ZavKom", 1740);
        ShedulePut("ZavKom", 1751);
        ShedulePut("ZavKom", 1802);
        ShedulePut("ZavKom", 1813);
        ShedulePut("ZavKom", 1824);
        ShedulePut("ZavKom", 1835);
        ShedulePut("ZavKom", 1846);
        ShedulePut("ZavKom", 1855);
        ShedulePut("ZavKom", 1908);
        ShedulePut("ZavKom", 1919);
        ShedulePut("ZavKom", 1928);
        ShedulePut("ZavKom", 1944);
        ShedulePut("ZavKom", 2000);
        ShedulePut("ZavKom", 2000);
        ShedulePut("ZavKom", 2016);
        ShedulePut("ZavKom", 2032);
        ShedulePut("ZavKom", 2048);
        ShedulePut("ZavKom", 2104);
        ShedulePut("ZavKom", 2120);
        ShedulePut("ZavKom", 2136);
        ShedulePut("ZavKom", 2152);
        ShedulePut("ZavKom", 2208);
        ShedulePut("ZavKom", 2224);
        ShedulePut("ZavKom", 2240);
        ShedulePut("ZavKom", 2256);
        ShedulePut("ZavKom", 2312);
        //Заводская -> Вокзальная по выходным дням:
        ShedulePut("HZavVok", 546);
        ShedulePut("HZavVok", 603);
        ShedulePut("HZavVok", 619);
        ShedulePut("HZavVok", 635);
        ShedulePut("HZavVok", 651);
        ShedulePut("HZavVok", 707);
        ShedulePut("HZavVok", 723);
        ShedulePut("HZavVok", 739);
        ShedulePut("HZavVok", 755);
        ShedulePut("HZavVok", 811);
        ShedulePut("HZavVok", 827);
        ShedulePut("HZavVok", 843);
        ShedulePut("HZavVok", 859);
        ShedulePut("HZavVok", 915);
        ShedulePut("HZavVok", 931);
        ShedulePut("HZavVok", 947);
        ShedulePut("HZavVok", 1003);
        ShedulePut("HZavVok", 1019);
        ShedulePut("HZavVok", 1035);
        ShedulePut("HZavVok", 1051);
        ShedulePut("HZavVok", 1107);
        ShedulePut("HZavVok", 1123);
        ShedulePut("HZavVok", 1139);
        ShedulePut("HZavVok", 1155);
        ShedulePut("HZavVok", 1211);
        ShedulePut("HZavVok", 1227);
        ShedulePut("HZavVok", 1243);
        ShedulePut("HZavVok", 1259);
        ShedulePut("HZavVok", 1315);
        ShedulePut("HZavVok", 1331);
        ShedulePut("HZavVok", 1347);
        ShedulePut("HZavVok", 1403);
        ShedulePut("HZavVok", 1419);
        ShedulePut("HZavVok", 1435);
        ShedulePut("HZavVok", 1451);
        ShedulePut("HZavVok", 1507);
        ShedulePut("HZavVok", 1523);
        ShedulePut("HZavVok", 1540);
        ShedulePut("HZavVok", 1556);
        ShedulePut("HZavVok", 1613);
        ShedulePut("HZavVok", 1629);
        ShedulePut("HZavVok", 1646);
        ShedulePut("HZavVok", 1702);
        ShedulePut("HZavVok", 1718);
        ShedulePut("HZavVok", 1734);
        ShedulePut("HZavVok", 1750);
        ShedulePut("HZavVok", 1806);
        ShedulePut("HZavVok", 1822);
        ShedulePut("HZavVok", 1838);
        ShedulePut("HZavVok", 1854);
        ShedulePut("HZavVok", 1910);
        ShedulePut("HZavVok", 1926);
        ShedulePut("HZavVok", 1942);
        ShedulePut("HZavVok", 1958);
        ShedulePut("HZavVok", 2014);
        ShedulePut("HZavVok", 2030);
        ShedulePut("HZavVok", 2046);
        ShedulePut("HZavVok", 2102);
        ShedulePut("HZavVok", 2118);
        ShedulePut("HZavVok", 2134);
        ShedulePut("HZavVok", 2150);
        ShedulePut("HZavVok", 2206);
        ShedulePut("HZavVok", 2222);
        ShedulePut("HZavVok", 2238);
        ShedulePut("HZavVok", 2254);
        ShedulePut("HZavVok", 2310);
        //Заводская -> Коммунаровская по выходным дням:
        ShedulePut("HZavKom", 548);
        ShedulePut("HZavKom", 605);
        ShedulePut("HZavKom", 621);
        ShedulePut("HZavKom", 637);
        ShedulePut("HZavKom", 653);
        ShedulePut("HZavKom", 709);
        ShedulePut("HZavKom", 725);
        ShedulePut("HZavKom", 741);
        ShedulePut("HZavKom", 757);
        ShedulePut("HZavKom", 813);
        ShedulePut("HZavKom", 829);
        ShedulePut("HZavKom", 845);
        ShedulePut("HZavKom", 901);
        ShedulePut("HZavKom", 917);
        ShedulePut("HZavKom", 933);
        ShedulePut("HZavKom", 949);
        ShedulePut("HZavKom", 1005);
        ShedulePut("HZavKom", 1021);
        ShedulePut("HZavKom", 1037);
        ShedulePut("HZavKom", 1053);
        ShedulePut("HZavKom", 1109);
        ShedulePut("HZavKom", 1125);
        ShedulePut("HZavKom", 1141);
        ShedulePut("HZavKom", 1157);
        ShedulePut("HZavKom", 1213);
        ShedulePut("HZavKom", 1229);
        ShedulePut("HZavKom", 1245);
        ShedulePut("HZavKom", 1301);
        ShedulePut("HZavKom", 1317);
        ShedulePut("HZavKom", 1333);
        ShedulePut("HZavKom", 1349);
        ShedulePut("HZavKom", 1405);
        ShedulePut("HZavKom", 1421);
        ShedulePut("HZavKom", 1437);
        ShedulePut("HZavKom", 1453);
        ShedulePut("HZavKom", 1509);
        ShedulePut("HZavKom", 1525);
        ShedulePut("HZavKom", 1542);
        ShedulePut("HZavKom", 1558);
        ShedulePut("HZavKom", 1615);
        ShedulePut("HZavKom", 1631);
        ShedulePut("HZavKom", 1647);
        ShedulePut("HZavKom", 1704);
        ShedulePut("HZavKom", 1720);
        ShedulePut("HZavKom", 1736);
        ShedulePut("HZavKom", 1752);
        ShedulePut("HZavKom", 1808);
        ShedulePut("HZavKom", 1824);
        ShedulePut("HZavKom", 1840);
        ShedulePut("HZavKom", 1856);
        ShedulePut("HZavKom", 1912);
        ShedulePut("HZavKom", 1927);
        ShedulePut("HZavKom", 1944);
        ShedulePut("HZavKom", 2000);
        ShedulePut("HZavKom", 2016);
        ShedulePut("HZavKom", 2033);
        ShedulePut("HZavKom", 2048);
        ShedulePut("HZavKom", 2104);
        ShedulePut("HZavKom", 2120);
        ShedulePut("HZavKom", 2136);
        ShedulePut("HZavKom", 2152);
        ShedulePut("HZavKom", 2208);
        ShedulePut("HZavKom", 2224);
        ShedulePut("HZavKom", 2240);
        ShedulePut("HZavKom", 2256);
        ShedulePut("HZavKom", 2312);
        //роспект Свободы -> Вокзальная по рабочим дням:
        ShedulePut("PrSvVok", 541);
        ShedulePut("PrSvVok", 552);
        ShedulePut("PrSvVok", 604);
        ShedulePut("PrSvVok", 615);
        ShedulePut("PrSvVok", 626);
        ShedulePut("PrSvVok", 637);
        ShedulePut("PrSvVok", 645);
        ShedulePut("PrSvVok", 651);
        ShedulePut("PrSvVok", 658);
        ShedulePut("PrSvVok", 703);
        ShedulePut("PrSvVok", 710);
        ShedulePut("PrSvVok", 717);
        ShedulePut("PrSvVok", 724);
        ShedulePut("PrSvVok", 731);
        ShedulePut("PrSvVok", 738);
        ShedulePut("PrSvVok", 746);
        ShedulePut("PrSvVok", 754);
        ShedulePut("PrSvVok", 803);
        ShedulePut("PrSvVok", 811);
        ShedulePut("PrSvVok", 819);
        ShedulePut("PrSvVok", 826);
        ShedulePut("PrSvVok", 835);
        ShedulePut("PrSvVok", 843);
        ShedulePut("PrSvVok", 853);
        ShedulePut("PrSvVok", 904);
        ShedulePut("PrSvVok", 915);
        ShedulePut("PrSvVok", 926);
        ShedulePut("PrSvVok", 936);
        ShedulePut("PrSvVok", 947);
        ShedulePut("PrSvVok", 959);
        ShedulePut("PrSvVok", 1014);
        ShedulePut("PrSvVok", 1031);
        ShedulePut("PrSvVok", 1047);
        ShedulePut("PrSvVok", 1103);
        ShedulePut("PrSvVok", 1119);
        ShedulePut("PrSvVok", 1135);
        ShedulePut("PrSvVok", 1151);
        ShedulePut("PrSvVok", 1207);
        ShedulePut("PrSvVok", 1223);
        ShedulePut("PrSvVok", 1239);
        ShedulePut("PrSvVok", 1255);
        ShedulePut("PrSvVok", 1311);
        ShedulePut("PrSvVok", 1327);
        ShedulePut("PrSvVok", 1343);
        ShedulePut("PrSvVok", 1359);
        ShedulePut("PrSvVok", 1415);
        ShedulePut("PrSvVok", 1431);
        ShedulePut("PrSvVok", 1445);
        ShedulePut("PrSvVok", 1456);
        ShedulePut("PrSvVok", 1507);
        ShedulePut("PrSvVok", 1518);
        ShedulePut("PrSvVok", 1529);
        ShedulePut("PrSvVok", 1540);
        ShedulePut("PrSvVok", 1551);
        ShedulePut("PrSvVok", 1602);
        ShedulePut("PrSvVok", 1613);
        ShedulePut("PrSvVok", 1624);
        ShedulePut("PrSvVok", 1635);
        ShedulePut("PrSvVok", 1646);
        ShedulePut("PrSvVok", 1657);
        ShedulePut("PrSvVok", 1708);
        ShedulePut("PrSvVok", 1719);
        ShedulePut("PrSvVok", 1730);
        ShedulePut("PrSvVok", 1741);
        ShedulePut("PrSvVok", 1752);
        ShedulePut("PrSvVok", 1803);
        ShedulePut("PrSvVok", 1814);
        ShedulePut("PrSvVok", 1825);
        ShedulePut("PrSvVok", 1835);
        ShedulePut("PrSvVok", 1847);
        ShedulePut("PrSvVok", 1858);
        ShedulePut("PrSvVok", 1907);
        ShedulePut("PrSvVok", 1923);
        ShedulePut("PrSvVok", 1939);
        ShedulePut("PrSvVok", 1955);
        ShedulePut("PrSvVok", 2011);
        ShedulePut("PrSvVok", 2027);
        ShedulePut("PrSvVok", 2043);
        ShedulePut("PrSvVok", 2059);
        ShedulePut("PrSvVok", 2115);
        ShedulePut("PrSvVok", 2131);
        ShedulePut("PrSvVok", 2147);
        ShedulePut("PrSvVok", 2203);
        ShedulePut("PrSvVok", 2219);
        ShedulePut("PrSvVok", 2235);
        ShedulePut("PrSvVok", 2251);
        ShedulePut("PrSvVok", 2307);
        //Проспект Свободы -> Коммунаровская по рабочим дням:
        ShedulePut("PrSvKom", 551);
        ShedulePut("PrSvKom", 603);
        ShedulePut("PrSvKom", 615);
        ShedulePut("PrSvKom", 627);
        ShedulePut("PrSvKom", 638);
        ShedulePut("PrSvKom", 649);
        ShedulePut("PrSvKom", 700);
        ShedulePut("PrSvKom", 708);
        ShedulePut("PrSvKom", 714);
        ShedulePut("PrSvKom", 720);
        ShedulePut("PrSvKom", 726);
        ShedulePut("PrSvKom", 733);
        ShedulePut("PrSvKom", 740);
        ShedulePut("PrSvKom", 747);
        ShedulePut("PrSvKom", 754);
        ShedulePut("PrSvKom", 801);
        ShedulePut("PrSvKom", 809);
        ShedulePut("PrSvKom", 817);
        ShedulePut("PrSvKom", 826);
        ShedulePut("PrSvKom", 834);
        ShedulePut("PrSvKom", 841);
        ShedulePut("PrSvKom", 849);
        ShedulePut("PrSvKom", 858);
        ShedulePut("PrSvKom", 906);
        ShedulePut("PrSvKom", 916);
        ShedulePut("PrSvKom", 926);
        ShedulePut("PrSvKom", 938);
        ShedulePut("PrSvKom", 949);
        ShedulePut("PrSvKom", 1001);
        ShedulePut("PrSvKom", 1011);
        ShedulePut("PrSvKom", 1022);
        ShedulePut("PrSvKom", 1038);
        ShedulePut("PrSvKom", 1054);
        ShedulePut("PrSvKom", 1110);
        ShedulePut("PrSvKom", 1126);
        ShedulePut("PrSvKom", 1142);
        ShedulePut("PrSvKom", 1158);
        ShedulePut("PrSvKom", 1214);
        ShedulePut("PrSvKom", 1230);
        ShedulePut("PrSvKom", 1246);
        ShedulePut("PrSvKom", 1302);
        ShedulePut("PrSvKom", 1318);
        ShedulePut("PrSvKom", 1334);
        ShedulePut("PrSvKom", 1350);
        ShedulePut("PrSvKom", 1406);
        ShedulePut("PrSvKom", 1422);
        ShedulePut("PrSvKom", 1438);
        ShedulePut("PrSvKom", 1455);
        ShedulePut("PrSvKom", 1509);
        ShedulePut("PrSvKom", 1519);
        ShedulePut("PrSvKom", 1531);
        ShedulePut("PrSvKom", 1542);
        ShedulePut("PrSvKom", 1553);
        ShedulePut("PrSvKom", 1603);
        ShedulePut("PrSvKom", 1615);
        ShedulePut("PrSvKom", 1626);
        ShedulePut("PrSvKom", 1637);
        ShedulePut("PrSvKom", 1648);
        ShedulePut("PrSvKom", 1659);
        ShedulePut("PrSvKom", 1710);
        ShedulePut("PrSvKom", 1721);
        ShedulePut("PrSvKom", 1731);
        ShedulePut("PrSvKom", 1743);
        ShedulePut("PrSvKom", 1754);
        ShedulePut("PrSvKom", 1805);
        ShedulePut("PrSvKom", 1816);
        ShedulePut("PrSvKom", 1826);
        ShedulePut("PrSvKom", 1838);
        ShedulePut("PrSvKom", 1849);
        ShedulePut("PrSvKom", 1858);
        ShedulePut("PrSvKom", 1910);
        ShedulePut("PrSvKom", 1922);
        ShedulePut("PrSvKom", 1930);
        ShedulePut("PrSvKom", 1946);
        ShedulePut("PrSvKom", 2002);
        ShedulePut("PrSvKom", 2018);
        ShedulePut("PrSvKom", 2034);
        ShedulePut("PrSvKom", 2050);
        ShedulePut("PrSvKom", 2106);
        ShedulePut("PrSvKom", 2122);
        ShedulePut("PrSvKom", 2138);
        ShedulePut("PrSvKom", 2154);
        ShedulePut("PrSvKom", 2210);
        ShedulePut("PrSvKom", 2226);
        ShedulePut("PrSvKom", 2242);
        ShedulePut("PrSvKom", 2248);
        ShedulePut("PrSvKom", 2314);
        //Проспект Свободы -> Вокзальная по выходным дням:
        ShedulePut("HPrSvVok", 543);
        ShedulePut("HPrSvVok", 600);
        ShedulePut("HPrSvVok", 616);
        ShedulePut("HPrSvVok", 632);
        ShedulePut("HPrSvVok", 643);
        ShedulePut("HPrSvVok", 704);
        ShedulePut("HPrSvVok", 720);
        ShedulePut("HPrSvVok", 736);
        ShedulePut("HPrSvVok", 752);
        ShedulePut("HPrSvVok", 803);
        ShedulePut("HPrSvVok", 824);
        ShedulePut("HPrSvVok", 840);
        ShedulePut("HPrSvVok", 856);
        ShedulePut("HPrSvVok", 912);
        ShedulePut("HPrSvVok", 928);
        ShedulePut("HPrSvVok", 944);
        ShedulePut("HPrSvVok", 1000);
        ShedulePut("HPrSvVok", 1016);
        ShedulePut("HPrSvVok", 1032);
        ShedulePut("HPrSvVok", 1048);
        ShedulePut("HPrSvVok", 1104);
        ShedulePut("HPrSvVok", 1120);
        ShedulePut("HPrSvVok", 1136);
        ShedulePut("HPrSvVok", 1152);
        ShedulePut("HPrSvVok", 1208);
        ShedulePut("HPrSvVok", 1224);
        ShedulePut("HPrSvVok", 1240);
        ShedulePut("HPrSvVok", 1256);
        ShedulePut("HPrSvVok", 1312);
        ShedulePut("HPrSvVok", 1328);
        ShedulePut("HPrSvVok", 1344);
        ShedulePut("HPrSvVok", 1400);
        ShedulePut("HPrSvVok", 1416);
        ShedulePut("HPrSvVok", 1432);
        ShedulePut("HPrSvVok", 1448);
        ShedulePut("HPrSvVok", 1504);
        ShedulePut("HPrSvVok", 1521);
        ShedulePut("HPrSvVok", 1537);
        ShedulePut("HPrSvVok", 1554);
        ShedulePut("HPrSvVok", 1610);
        ShedulePut("HPrSvVok", 1627);
        ShedulePut("HPrSvVok", 1643);
        ShedulePut("HPrSvVok", 1659);
        ShedulePut("HPrSvVok", 1715);
        ShedulePut("HPrSvVok", 1731);
        ShedulePut("HPrSvVok", 1747);
        ShedulePut("HPrSvVok", 1803);
        ShedulePut("HPrSvVok", 1819);
        ShedulePut("HPrSvVok", 1835);
        ShedulePut("HPrSvVok", 1851);
        ShedulePut("HPrSvVok", 1907);
        ShedulePut("HPrSvVok", 1923);
        ShedulePut("HPrSvVok", 1939);
        ShedulePut("HPrSvVok", 1955);
        ShedulePut("HPrSvVok", 2011);
        ShedulePut("HPrSvVok", 2027);
        ShedulePut("HPrSvVok", 2043);
        ShedulePut("HPrSvVok", 2059);
        ShedulePut("HPrSvVok", 2115);
        ShedulePut("HPrSvVok", 2131);
        ShedulePut("HPrSvVok", 2147);
        ShedulePut("HPrSvVok", 2203);
        ShedulePut("HPrSvVok", 2219);
        ShedulePut("HPrSvVok", 2235);
        ShedulePut("HPrSvVok", 2251);
        ShedulePut("HPrSvVok", 2307);
        //Проспект Свободы -> Комунаровская по выходным дням
        ShedulePut("HPrSvKom", 551);
        ShedulePut("HPrSvKom", 607);
        ShedulePut("HPrSvKom", 623);
        ShedulePut("HPrSvKom", 639);
        ShedulePut("HPrSvKom", 650);
        ShedulePut("HPrSvKom", 701);
        ShedulePut("HPrSvKom", 713);
        ShedulePut("HPrSvKom", 724);
        ShedulePut("HPrSvKom", 735);
        ShedulePut("HPrSvKom", 746);
        ShedulePut("HPrSvKom", 757);
        ShedulePut("HPrSvKom", 808);
        ShedulePut("HPrSvKom", 819);
        ShedulePut("HPrSvKom", 830);
        ShedulePut("HPrSvKom", 841);
        ShedulePut("HPrSvKom", 852);
        ShedulePut("HPrSvKom", 903);
        ShedulePut("HPrSvKom", 914);
        ShedulePut("HPrSvKom", 925);
        ShedulePut("HPrSvKom", 936);
        ShedulePut("HPrSvKom", 947);
        ShedulePut("HPrSvKom", 958);
        ShedulePut("HPrSvKom", 1009);
        ShedulePut("HPrSvKom", 1020);
        ShedulePut("HPrSvKom", 1031);
        ShedulePut("HPrSvKom", 1042);
        ShedulePut("HPrSvKom", 1053);
        ShedulePut("HPrSvKom", 1104);
        ShedulePut("HPrSvKom", 1115);
        ShedulePut("HPrSvKom", 1126);
        ShedulePut("HPrSvKom", 1137);
        ShedulePut("HPrSvKom", 1148);
        ShedulePut("HPrSvKom", 1159);
        ShedulePut("HPrSvKom", 1210);
        ShedulePut("HPrSvKom", 1221);
        ShedulePut("HPrSvKom", 1232);
        ShedulePut("HPrSvKom", 1243);
        ShedulePut("HPrSvKom", 1254);
        ShedulePut("HPrSvKom", 1305);
        ShedulePut("HPrSvKom", 1316);
        ShedulePut("HPrSvKom", 1327);
        ShedulePut("HPrSvKom", 1338);
        ShedulePut("HPrSvKom", 1349);
        ShedulePut("HPrSvKom", 1400);
        ShedulePut("HPrSvKom", 1411);
        ShedulePut("HPrSvKom", 1422);
        ShedulePut("HPrSvKom", 1432);
        ShedulePut("HPrSvKom", 1444);
        ShedulePut("HPrSvKom", 1455);
        ShedulePut("HPrSvKom", 1506);
        ShedulePut("HPrSvKom", 1517);
        ShedulePut("HPrSvKom", 1528);
        ShedulePut("HPrSvKom", 1539);
        ShedulePut("HPrSvKom", 1550);
        ShedulePut("HPrSvKom", 1600);
        ShedulePut("HPrSvKom", 1611);
        ShedulePut("HPrSvKom", 1622);
        ShedulePut("HPrSvKom", 1634);
        ShedulePut("HPrSvKom", 1650);
        ShedulePut("HPrSvKom", 1706);
        ShedulePut("HPrSvKom", 1722);
        ShedulePut("HPrSvKom", 1738);
        ShedulePut("HPrSvKom", 1754);
        ShedulePut("HPrSvKom", 1810);
        ShedulePut("HPrSvKom", 1826);
        ShedulePut("HPrSvKom", 1842);
        ShedulePut("HPrSvKom", 1858);
        ShedulePut("HPrSvKom", 1914);
        ShedulePut("HPrSvKom", 1918);
        ShedulePut("HPrSvKom", 1930);
        ShedulePut("HPrSvKom", 1946);
        ShedulePut("HPrSvKom", 2002);
        ShedulePut("HPrSvKom", 2018);
        ShedulePut("HPrSvKom", 2034);
        ShedulePut("HPrSvKom", 2050);
        ShedulePut("HPrSvKom", 2106);
        ShedulePut("HPrSvKom", 2122);
        ShedulePut("HPrSvKom", 2138);
        ShedulePut("HPrSvKom", 2154);
        ShedulePut("HPrSvKom", 2210);
        ShedulePut("HPrSvKom", 2226);
        ShedulePut("HPrSvKom", 2242);
        ShedulePut("HPrSvKom", 2258);

        //Коммунаровская -> Вокзальная по рабочим дням:

        ShedulePut("KomVok", 538);
        ShedulePut("KomVok", 550);
        ShedulePut("KomVok", 602);
        ShedulePut("KomVok", 613);
        ShedulePut("KomVok", 624);
        ShedulePut("KomVok", 635);
        ShedulePut("KomVok", 643);
        ShedulePut("KomVok", 649);
        ShedulePut("KomVok", 655);
        ShedulePut("KomVok", 701);
        ShedulePut("KomVok", 708);
        ShedulePut("KomVok", 715);
        ShedulePut("KomVok", 722);
        ShedulePut("KomVok", 729);
        ShedulePut("KomVok", 736);
        ShedulePut("KomVok", 744);
        ShedulePut("KomVok", 752);
        ShedulePut("KomVok", 801);
        ShedulePut("KomVok", 809);
        ShedulePut("KomVok", 816);
        ShedulePut("KomVok", 824);
        ShedulePut("KomVok", 833);
        ShedulePut("KomVok", 841);
        ShedulePut("KomVok", 851);
        ShedulePut("KomVok", 902);
        ShedulePut("KomVok", 913);
        ShedulePut("KomVok", 924);
        ShedulePut("KomVok", 934);
        ShedulePut("KomVok", 945);
        ShedulePut("KomVok", 956);
        ShedulePut("KomVok", 1012);
        ShedulePut("KomVok", 1029);
        ShedulePut("KomVok", 1045);
        ShedulePut("KomVok", 1101);
        ShedulePut("KomVok", 1117);
        ShedulePut("KomVok", 1133);
        ShedulePut("KomVok", 1149);
        ShedulePut("KomVok", 1205);
        ShedulePut("KomVok", 1221);
        ShedulePut("KomVok", 1237);
        ShedulePut("KomVok", 1253);
        ShedulePut("KomVok", 1309);
        ShedulePut("KomVok", 1325);
        ShedulePut("KomVok", 1341);
        ShedulePut("KomVok", 1357);
        ShedulePut("KomVok", 1413);
        ShedulePut("KomVok", 1429);
        ShedulePut("KomVok", 1442);
        ShedulePut("KomVok", 1454);
        ShedulePut("KomVok", 1509);
        ShedulePut("KomVok", 1515);
        ShedulePut("KomVok", 1526);
        ShedulePut("KomVok", 1537);
        ShedulePut("KomVok", 1548);
        ShedulePut("KomVok", 1559);
        ShedulePut("KomVok", 1610);
        ShedulePut("KomVok", 1621);
        ShedulePut("KomVok", 1632);
        ShedulePut("KomVok", 1643);
        ShedulePut("KomVok", 1654);
        ShedulePut("KomVok", 1705);
        ShedulePut("KomVok", 1716);
        ShedulePut("KomVok", 1727);
        ShedulePut("KomVok", 1738);
        ShedulePut("KomVok", 1749);
        ShedulePut("KomVok", 1800);
        ShedulePut("KomVok", 1811);
        ShedulePut("KomVok", 1822);
        ShedulePut("KomVok", 1833);
        ShedulePut("KomVok", 1845);
        ShedulePut("KomVok", 1855);
        ShedulePut("KomVok", 1905);
        ShedulePut("KomVok", 1921);
        ShedulePut("KomVok", 1837);
        ShedulePut("KomVok", 1953);
        ShedulePut("KomVok", 2009);
        ShedulePut("KomVok", 2025);
        ShedulePut("KomVok", 2041);
        ShedulePut("KomVok", 2057);
        ShedulePut("KomVok", 2113);
        ShedulePut("KomVok", 2129);
        ShedulePut("KomVok", 2145);
        ShedulePut("KomVok", 2201);
        ShedulePut("KomVok", 2217);
        ShedulePut("KomVok", 2233);
        ShedulePut("KomVok", 2249);
        ShedulePut("KomVok", 2305);
//Коммунаровская -> Вокзальная по выходным дням:
        ShedulePut("HKomVok", 540);
        ShedulePut("HKomVok", 558);
        ShedulePut("HKomVok", 614);
        ShedulePut("HKomVok", 630);
        ShedulePut("HKomVok", 646);
        ShedulePut("HKomVok", 702);
        ShedulePut("HKomVok", 718);
        ShedulePut("HKomVok", 734);
        ShedulePut("HKomVok", 750);
        ShedulePut("HKomVok", 806);
        ShedulePut("HKomVok", 822);
        ShedulePut("HKomVok", 838);
        ShedulePut("HKomVok", 854);
        ShedulePut("HKomVok", 910);
        ShedulePut("HKomVok", 926);
        ShedulePut("HKomVok", 942);
        ShedulePut("HKomVok", 958);
        ShedulePut("HKomVok", 1014);
        ShedulePut("HKomVok", 1030);
        ShedulePut("HKomVok", 1046);
        ShedulePut("HKomVok", 1102);
        ShedulePut("HKomVok", 1118);
        ShedulePut("HKomVok", 1134);
        ShedulePut("HKomVok", 1150);
        ShedulePut("HKomVok", 1206);
        ShedulePut("HKomVok", 1222);
        ShedulePut("HKomVok", 1238);
        ShedulePut("HKomVok", 1254);
        ShedulePut("HKomVok", 1310);
        ShedulePut("HKomVok", 1326);
        ShedulePut("HKomVok", 1342);
        ShedulePut("HKomVok", 1358);
        ShedulePut("HKomVok", 1414);
        ShedulePut("HKomVok", 1430);
        ShedulePut("HKomVok", 1446);
        ShedulePut("HKomVok", 1502);
        ShedulePut("HKomVok", 1518);
        ShedulePut("HKomVok", 1535);
        ShedulePut("HKomVok", 1551);
        ShedulePut("HKomVok", 1608);
        ShedulePut("HKomVok", 1624);
        ShedulePut("HKomVok", 1641);
        ShedulePut("HKomVok", 1657);
        ShedulePut("HKomVok", 1713);
        ShedulePut("HKomVok", 1729);
        ShedulePut("HKomVok", 1745);
        ShedulePut("HKomVok", 1801);
        ShedulePut("HKomVok", 1817);
        ShedulePut("HKomVok", 1833);
        ShedulePut("HKomVok", 1849);
        ShedulePut("HKomVok", 1905);
        ShedulePut("HKomVok", 1921);
        ShedulePut("HKomVok", 1937);
        ShedulePut("HKomVok", 1953);
        ShedulePut("HKomVok", 2009);
        ShedulePut("HKomVok", 2025);
        ShedulePut("HKomVok", 2041);
        ShedulePut("HKomVok", 2057);
        ShedulePut("HKomVok", 2113);
        ShedulePut("HKomVok", 2129);
        ShedulePut("HKomVok", 2145);
        ShedulePut("HKomVok", 2201);
        ShedulePut("HKomVok", 2217);
        ShedulePut("HKomVok", 2233);
        ShedulePut("HKomVok", 2249);
        ShedulePut("HKomVok", 2305);


    }

    public int Time_Plus(int a, int b) {
        String newTime = "";
        try {
            String myTime = ConvertTime(a);
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            Date d = new Date();
            d = df.parse(myTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.MINUTE, b);
            newTime = df.format(cal.getTime());
            return ConvertOfTime(newTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //ТУТ НУЖНО ИСКАТЬ
    int Search(int noowtime, String Napr) {
        int res = 0;
        Log.v("Metr", "Serch " + noowtime);
        for (int i = 0; i < 1700; i++) {
            if (ShedName[i].equals(Napr)) {
                if (ShedTime[i] > noowtime) {
                    res = ShedTime[i];

                    break;
                }
            }

        }


        return res;
    }

    public int TimeToFinish(String str, ArrayList Arr, int nowtime, int a) {
        int sumtime1 = 0;
        boolean flag = false;
        int res = 0;
        for (Object key : Arr) {
            if (key.toString().equals(str) || flag) {
                flag = true;
                if (sumtime1 == 0) {
                    sumtime1 = Search(nowtime, key.toString());
                } else {
                    int k;
                    k = sumtime1;

                    sumtime1 = Search(sumtime1, key.toString());

                    res += Time_Minus(k, sumtime1);
                    nowtime = (Time_Plus(nowtime, res));
                }
            }
        }
        return res + a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        int densityDpi = dm.densityDpi;
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        final ScreenMetr metrics = getInstance();
        metrics.initDisplay(densityDpi, width, height);

        //drawView=new DrawView(MyCont);




        setContentView(R.layout.activity_main);




        ShowInfText = (TextView) findViewById(R.id.Test);
        ShowInfmapText = (TextView) findViewById(R.id.Test2);

        String[] list = getResources().getStringArray(R.array.station);
        String[] to = getResources().getStringArray(R.array.to);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinnerstyle, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1 = (MaterialSpinner) findViewById(R.id.spinner1);
        spinner1.setAdapter(adapter);

        ArrayAdapter<String> toAdapter = new ArrayAdapter<String>(this, R.layout.spinnertwo, to);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2 = (MaterialSpinner) findViewById(R.id.spinner2);
        spinner2.setAdapter(toAdapter);

        FirstSwitch=(CheckBox)findViewById(R.id.switch1);
        lvMain = (ListView) findViewById(R.id.listView);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    String names[]={"Здесь будет рассписание","item2","item2","item3","item4","item5","item6","item7","item8","item2","Здесь будет рассписание","item2","item2","item3","item4","item5","item6","item7","item8","item2","item2","item2"};

                    keyFirst="";
                    if(!FirstSwitch.isChecked())
                    {
                        keyFirst="H";
                    }
                    // создаем адаптер

                    switch(position)
                    {
                        //H - hollyday
                        //Vok - Vokzal
                        //Met -Metrostroiteli
                        //Lurg - metalurgovskaya
                        //Zav - zavodskaya
                        //PrSv - ProspektSvobotu
                        //Kom - komunarovskaya

                        case 0: keyFirst+="Vok"; break;
                        case 1: keyFirst+="Met"; break;
                        case 2: keyFirst+="Lurg"; break;
                        case 3: keyFirst+="Zav"; break;
                        case 4: keyFirst+="PrSv"; break;
                        case 5: keyFirst+="Kom"; break;

                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                   switch (position) {
                                                       //H - hollyday
                                                       //Vok - Vokzal
                                                       //Met -Metrostroiteli
                                                       //Lurg - metalurgovskaya
                                                       //Zav - zavodskaya
                                                       //PrSv - ProspektSvobotu
                                                       //Kom - komunarovskaya

                                                       case 0:
                                                           keyTwo = "Vok";
                                                           break;
                                                       case 1:
                                                           keyTwo= "Kom";
                                                           break;


                                                   }
                                                   if (position!=-1) {

                                                       for (int i = 0; i < 1700; i++) {
                                                           if ((keyFirst.concat(keyTwo)).equals(ShedName[i])) {
                                                               Raspto.add(ConvertTime(ShedTime[i]));

                                                           }


                                                           ListAdapter listadapter = new ArrayAdapter<String>(view.getContext(), R.layout.listbody, R.id.list_content, Raspto);

                                                           lvMain.setAdapter(listadapter);

                                                       }


                                                   }


                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> parent) {

                                               }
                                           }
            );





        spinner1.setVisibility(View.INVISIBLE);
        spinner2.setVisibility(View.INVISIBLE);
        lvMain.setVisibility(View.INVISIBLE);
        FirstSwitch.setVisibility(View.INVISIBLE);











//add arraylist list ostanovok
        OstToVokzal = new ArrayList<String>();
        OstToKomunar = new ArrayList<String>();
        if (WorkedDay()) {
            //H - hollyday
            //Vok - Vokzal
            //Met -Metrostroiteli
            //Lurg - metalurgovskaya
            //Zav - zavodskaya
            //PrSv - ProspektSvobotu
            //Kom - komunarovskaya
            OstToVokzal.add("KomVok");
            OstToVokzal.add("PrSvVok");
            OstToVokzal.add("ZavVok");
            OstToVokzal.add("LurgVok");
            OstToVokzal.add("MetVok");


            OstToKomunar.add("VokKom");
            OstToKomunar.add("MetKom");
            OstToKomunar.add("LurgKom");
            OstToKomunar.add("ZavKom");
            OstToKomunar.add("PrSvKom");


        } else {
            OstToVokzal.add("HKomVok");
            OstToVokzal.add("HPrSvVok");
            OstToVokzal.add("HZavVok");
            OstToVokzal.add("HLurgVok");
            OstToVokzal.add("HMetVok");


            OstToKomunar.add("HVokKom");
            OstToKomunar.add("HMetKom");
            OstToKomunar.add("HLurgKom");
            OstToKomunar.add("HZavKom");
            OstToKomunar.add("HPrSvKom");

        }


        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //init station
        if (WorkedDay()) {
            NaprFirst = "KomVok";
            NaprTwo = "NULL";
            metrics.setNapX(NaprFirst);
            metrics.setNapY(NaprTwo);
        } else {
            NaprFirst = "HKomVok";
            NaprTwo = "NULL";
            metrics.setNapX(NaprFirst);
            metrics.setNapY(NaprTwo);
        }

        mToolbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                }
                return false;
            }
        });

        mNavigationNavDrawerFragment = (Nav_DrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationNavDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);



        ShowInfText.setTypeface(Typeface.createFromAsset(
                getAssets(), "Roboto-Light.ttf"));
        ShowInfmapText.setTypeface(Typeface.createFromAsset(
                getAssets(), "Roboto-Light.ttf"));


        init();


// Отобразим набор


        //вывод по таймеру
        Timer timer = new Timer();
        timer.schedule(new TimerTask() { // ќпредел¤ем задачу
            @Override
            public void run() {
                Thread t = new Thread() {
                    @Override
                    public void run() {

                        try {
                            //вывод в ранебл времени
                            Runnable mUpdateUITimerTask = new Runnable() {
                                public void run() {
                                    // do whatever you want to change here, like:

                                    ScreenMetr screen = getInstance();
                                    NaprFirst = screen.getNapX();
                                    NaprTwo = screen.getNapY();


                                    modiX = Search(parsetime, NaprFirst);
                                    modiY = Search(parsetime, NaprTwo);

                                    modiX1 = Search(modiX, NaprFirst);
                                    modiY1 = Search(modiY, NaprTwo);

                                    modiX2 = Search(modiX1, NaprFirst);
                                    modiY2 = Search(modiY1, NaprTwo);

                                    //TimeToFinish(NaprFirst,OstToVokzal,parsetime,0);
                                    //TimeToFinish(NaprFirst,OstToVokzal,modiX,0);
                                    //TimeToFinish(NaprFirst,OstToVokzal,modiX1,0);

                                    //  metrics.initModi(ConvertTime( modiX),ConvertTime(modiY),ConvertTime( modiX1),ConvertTime(modiY1),ConvertTime(modiX2),ConvertTime(modiY2));


                                    if (!NaprFirst.equals("NULL")) {
                                        //Расчитывает время прибытия не точно
                                        //   ShowInfmapText.setText(  "На вокзал: \n "+TimeToFinish(NaprFirst, OstToVokzal, parsetime, 0)+" мин.\n слейдуйщие:\n"+TimeToFinish(NaprFirst,OstToVokzal,modiX1,0)+" мин.\n"+TimeToFinish(NaprFirst,OstToVokzal,modiX2,0)+" мин.\n");
                                        ShowInfmapText.setText("На вокзал:\n через " + Integer.toString(Time_Minus(modiX, parsetime)) + " мин.\n следующие:\n" + ConvertTime(modiX1) + " мин.\n" + ConvertTime(modiX2) + " мин.\n");
                                    } else {
                                        ShowInfmapText.setText("Конечная\nВокзал ");
                                    }

                                    if (!NaprTwo.equals("NULL")) {
                                        ShowInfText.setText("На коммунар:\n через " + Integer.toString(Time_Minus(modiY, parsetime)) + " мин.\n следующие:\n" + ConvertTime(modiY1) + " мин.\n" + ConvertTime(modiY2) + " мин.\n");
                                    } else {
                                        ShowInfText.setText("Конечная\nКоммунар ");
                                    }

                                }
                            };

                            Date currentDate = new Date();
                            // число и мес¤ц
                            Time = new SimpleDateFormat("HH mm");
                            newCal.setTime(newCal.getTime());
                            int day = newCal.get(Calendar.DAY_OF_WEEK);
                            parsetime = (Integer.parseInt(Time.format(currentDate).replaceAll(" ", "")));

                            // Out1=Time_Minus(Search(parsetime,"PrSvVok"), parsetime);

                            // вызов функции вывод ранебл
                            runOnUiThread(mUpdateUITimerTask);
                            //Log.v("Metr", Integer.toString(parsetime));


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    ;
                };
                t.start();
            }


        }, 0, 1000);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        //направление поезда
        //H - hollyday
        //Vok - Vokzal
        //Met -Metrostroiteli
        //Lurg - metalurgovskaya
        //Zav - zavodskaya
        //PrSv - ProspektSvobotu
        //Kom - komunarovskaya
        ScreenMetr screen = getInstance();
        View vt = findViewById(R.id.DrawV);

        modiX = modiX1 = modiX2 = 0;
        modiY = modiY1 = modiY2 = 0;
        if (position == 0) {
            try {
                if (!vt.isShown()) {
                    vt.setVisibility(View.VISIBLE);
                    ShowInfText.setVisibility(View.VISIBLE);
                    ShowInfmapText.setVisibility(View.VISIBLE);
                    spinner1.setVisibility(View.INVISIBLE);
                    spinner2.setVisibility(View.INVISIBLE);
                    lvMain.setVisibility(View.INVISIBLE);
                    FirstSwitch.setVisibility(View.INVISIBLE);
                }


            } catch (Exception e) {


            }
            NaprFirst = "KomVok";
            NaprTwo = "NULL";

        }
        if (position == 1) {

            try {
                if (!vt.isShown()) {
                    vt.setVisibility(View.VISIBLE);
                    ShowInfText.setVisibility(View.VISIBLE);
                    ShowInfmapText.setVisibility(View.VISIBLE);
                    spinner1.setVisibility(View.INVISIBLE);
                    spinner2.setVisibility(View.INVISIBLE);
                    lvMain.setVisibility(View.INVISIBLE);
                    FirstSwitch.setVisibility(View.INVISIBLE);
                }
            } catch (Exception e) {

                Log.d("DrawC", "EXeption on case click "+e);
            }
            NaprFirst = "PrSvVok";
            NaprTwo = "PrSvKom";

        }
        if (position == 2) {
            try {
                if (!vt.isShown()) {
                    vt.setVisibility(View.VISIBLE);
                    ShowInfText.setVisibility(View.VISIBLE);
                    ShowInfmapText.setVisibility(View.VISIBLE);
                    spinner1.setVisibility(View.INVISIBLE);
                    spinner2.setVisibility(View.INVISIBLE);
                    lvMain.setVisibility(View.INVISIBLE);
                    FirstSwitch.setVisibility(View.INVISIBLE);
                }
            } catch (Exception e) {


            }
            NaprFirst = "ZavVok";
            NaprTwo = "ZavKom";

        }
        if (position == 3) {

            try {
                if (!vt.isShown()) {
                    vt.setVisibility(View.VISIBLE);
                    ShowInfText.setVisibility(View.VISIBLE);
                    ShowInfmapText.setVisibility(View.VISIBLE);
                    spinner1.setVisibility(View.INVISIBLE);
                    spinner2.setVisibility(View.INVISIBLE);
                    lvMain.setVisibility(View.INVISIBLE);
                    FirstSwitch.setVisibility(View.INVISIBLE);
                }
            } catch (Exception e) {


            }
            NaprFirst = "LurgVok";
            NaprTwo = "LurgKom";

        }
        if (position == 4) {
            try {
                if (!vt.isShown()) {
                    vt.setVisibility(View.VISIBLE);
                    ShowInfText.setVisibility(View.VISIBLE);
                    ShowInfmapText.setVisibility(View.VISIBLE);
                    spinner1.setVisibility(View.INVISIBLE);
                    spinner2.setVisibility(View.INVISIBLE);
                    lvMain.setVisibility(View.INVISIBLE);
                    FirstSwitch.setVisibility(View.INVISIBLE);
                }
            } catch (Exception e) {


            }
            NaprFirst = "MetVok";
            NaprTwo = "MetKom";

        }
        if (position == 5) {
            try {
                if (!vt.isShown()) {
                    vt.setVisibility(View.VISIBLE);
                    ShowInfText.setVisibility(View.VISIBLE);
                    ShowInfmapText.setVisibility(View.VISIBLE);
                    spinner1.setVisibility(View.INVISIBLE);
                    spinner2.setVisibility(View.INVISIBLE);
                    lvMain.setVisibility(View.INVISIBLE);
                    FirstSwitch.setVisibility(View.INVISIBLE);
                }
            } catch (Exception e) {


            }
            NaprFirst = "NULL";
            NaprTwo = "VokKom";

        }
        if (position == 6) {

            try {
                if (vt.isShown()) {
                    vt.setVisibility(View.INVISIBLE);
                    ShowInfText.setVisibility(View.INVISIBLE);
                    ShowInfmapText.setVisibility(View.INVISIBLE);
                    spinner1.setVisibility(View.VISIBLE);
                    spinner2.setVisibility(View.VISIBLE);
                    lvMain.setVisibility(View.VISIBLE);
                    FirstSwitch.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {


            }
            NaprFirst = "RASP";
            NaprTwo = "RASP";


        }

        //Если выходной добавляем Н префикс для поиска направления по вых дню
        if (!WorkedDay()) {
            NaprFirst = "H" + NaprFirst;
            NaprTwo = "H" + NaprTwo;
        }
        screen.setNapX(NaprFirst);
        screen.setNapY(NaprTwo);

        //при клике на пункт меню
        Toast.makeText(this, "item no: " + position + " 1 " + NaprFirst + " 2 " + NaprTwo, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        if (mNavigationNavDrawerFragment.isDrawerOpen())
            mNavigationNavDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


}

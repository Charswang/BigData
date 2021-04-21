package test.leetcode;

import javax.jnlp.IntegrationService;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class Test {
    public static void main(String[] args) {
//        System.out.println(Character.isLetter('5'));
//        System.out.println(reverseOnlyLetters("ab-cd"));  // 917. 仅仅反转字母
//        System.out.println(isLongPressedName("vtkgn", "vttkgnn")); // 925. 长按键入
        /*String[] strs = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
        System.out.println(numUniqueEmails(strs));*/
//        System.out.println(gcdOfStrings("TAUXXTAUXXTAUXXTAUXXTAUXX","TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX")); //1071. 字符串的最大公因子
//        System.out.println(defangIPaddr("192.168.246.132")); // 1108、IP 地址无效化
//        System.out.println(maxNumberOfBalloons("loonbalxballpoon")); // 1189. “气球” 的最大数量
//        System.out.println(balancedStringSplit("RLRRLLRLRL"));  // 1221. 分割平衡字符串
//        System.out.println(freqAlphabets("10#11#12"));  // 1309. 解码字母到整数映射
//        System.out.println(sortString("ggggggg")); // 1370. 上升下降字符串
//        int[] height = {1,1};
//        System.out.println(maxArea(height));
//        int[] nums = {1,-1,-1,0};
//        System.out.println(threeSum(nums));

//        int[] nums = {0,0,0,0};
//        System.out.println(fourSum(nums, 0));

        /*ListNode listNode = new ListNode(1);
        ListNode l2 = new ListNode(2);
        listNode.next = l2;
        *//*ListNode l3 = new ListNode(3);
        l2.next = l3;
        ListNode l4 = new ListNode(4);
        l3.next = l4;
        ListNode l5 = new ListNode(5);
        l4.next = l5;*//*
        ListNode result = removeNthFromEnd(listNode,1);
        while(result!=null){
            System.out.println(result.val);
            result = result.next;
        }*/

        /*int[] nums = {0,0,1,1,1,1,2,3,3};
        System.out.println(removeDuplicates(nums));*/

        /*ListNode listNode = new ListNode(1);
        ListNode l2 = new ListNode(4);
        listNode.next = l2;
        ListNode l3 = new ListNode(3);
        l2.next = l3;
        ListNode l4 = new ListNode(2);
        l3.next = l4;
        ListNode l5 = new ListNode(5);
        l4.next = l5;
        ListNode l6 = new ListNode(2);
        l5.next = l6;
        ListNode result = partition(listNode,3);
        while(result!=null){
            System.out.println(result.val);
            result = result.next;
        }*/

        /*int target = 7;
        int[] nums = {5};
        System.out.println(minSubArrayLen(target,nums));*/

        // TreeMap与HashMap
        /*Map<String,Integer> treemap = new TreeMap<String,Integer>();
        treemap.put("a",null); // TreeMap中不能存放null
        for (Map.Entry<String,Integer> entry : treemap.entrySet()){
            System.out.println(entry.getKey() + "--" + entry.getValue());
        }
        Map<String,Integer> hashMap = new HashMap<String,Integer>();
        hashMap.put("kaa",1);
        hashMap.put("kc",3);
        hashMap.put("kkb",2);
        hashMap.put("kf",2);
        hashMap.put("e",2);
        hashMap.put("e",3);
        hashMap.put(null,null);
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()){
            System.out.println(entry.getKey() + "--" + entry.getValue());
        }
        System.out.println(hashMap.get("www"));
        System.out.println(treemap.get("www"));*/

//        System.out.println(characterReplacement("ABBB", 2));

        /*int[] nums = {-2,1,-1,-2,-2};
        System.out.println(circularArrayLoop(nums));*/

        /*int[][] nums = {{1,2},{3,4}};
        try{
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("./javayesy.text"),"UTF-8"));
            pw.write(nums[1][1]);

        }catch(Exception e){
            e.printStackTrace();
        }*/

        /*String s = "abcea";
        List<String> list = new ArrayList<String>();
        *//*list.add("ale");
        list.add("apple");
        list.add("monkey");
        list.add("plea");*//*
        list.add("abe");
        list.add("aba");
//        list.add("c");
        System.out.println(findLongestWord(s, list));*/

        /*int[] nums = {3, 1, 4, 1, 5};
        int k = 2;
        System.out.println(findPairs(nums, k));*/

        /*String s1 = "abhna";
        String s2 = "adhalhnbahahhh";*/
        /*String s1 = "kuzntqeuvaszrspkgjvxrupwxwrexztptsowceibeewxbslvosbobmyymikdscshybtmanuxeqtanrjekbwirmhgvfmzipfiqxcilarfyasoayepgfzmdytlpjymeaztsyubkbmblepwozffxiitdhwaquozlfmnascomqczrbhxcnzugppddtudxrigfeaozzojpeamnobapgwksudbiwdedvprwonmzardsodhxmkgghqzfhorjaijdvwzsnfpdfklwibbsnwqsoajcpjisbgizgttlnmclawbgnhbmtcpuusuammvgxnopdngclxumgfgwjrinamevhirpmlkwtyxkrmoffrreotdosjghsrkgxyiyrytbbofgczndgmdalyvvoljczcztxitxelywqemjigtuanubpstndwzvtiejtoqvetaehvcuujyupncumjnkesmoadzyvkwvjqgqewvvvpheyyvkewefbjjqzajxnhouodanyruqpzdcjmgnxkmhsgqjhpcyviewmrkfioudzqivmmguxjxuxdmpsmkwnvbxcomifgxqmcovlkooptjpfxjllwtlkkoaayzduodgsusaogswmoqkznynwiukkrrxzkwcknwlazxnlmghybxmyvquzbdqlpfydhnnuvlmyjmixyzso";
        String s2 = "zthosfejqodcstlqczkndmgwtcakxzxaklkrehkfwnokclametzpnblcwaspdblfoopsiqrpzlbmlysddlqxcjzezcpknwzljvhmqxqinmptcppipifchxexlytleambzwmqwgvxlehnecdqsqbrxqfwvcovgdvtmwbnvajvkizixbmuiuyuixjhiohimghdbohetogrhzsbzgpekosxcjglsrvzenovpjyzknuumpsdrufcjsyfbuwfmaaztxjbpjctnuwcqknnemptjbgfthyafeatskfmysaioqikcpywmefujnvthumyhareltknxyvqprexgbwyzodfkinltwobeukrmpyjkrgvwhbtnzaozgxouxndmkyvzlujhxxwebptykctbojgnvcwhhgsyohccrqxksdyygcwdsazlznhqjdddisjmfqvjqcquuvjrzkcvzpxpfakbkrqlzacanqbggavezedqmoffxmkrlcwxdeosvhvvknqimwpasrlldewvhppzixgcxisysgeppcwfknecupyrrqnkhvessintrequaqiuoesgyndovaqxnlldmdupjcjzejannfjfasguyvgsdakwxezrginhdstbrtqmznpkasytqtbfyftwhgnuazcwehsvcdukuifmkefzabxyhihgnldpsvglubalbsvqstfxehvnpxmrejnkqacafuvzghbttgqmjhqzejasoasbpjirfawcvwahykvrfpaadcgvxssebdznzyvamyilahahgdslwvpuvzsinbbqecdqyvwnucjzyxmxwqwyxbxoljnjcqqdumghmcvqpcpjlxemupospxvkicqvyiavatbojgzurfzitgpeqjmvsgzzqphciyweyavebslgegjcyqmgehchryyclswjequeijzpsvuercqzhwgtgyxhxavhqkrwqdvkqvklicxpasnsnbgybtufdgbwrpaewzwczabckvddtewunsktcznqkivsubdjrpzxtsohiilcybrwqtlfqmjzmvpbfjmbjvbpwietkyzwzizwuiohjuhoekejhmkrooeyydmavdencmxhsxdnyitzivlymzyqogdhzrwhbdupborzqihurziajjwbrfwkzgbpmfgtobkbzyijcgkszyuyouyxvztmmtaqetcaxqrkbrmkzyokglckifgdfidjqoiqrfrpftagxxoqodbyygdioxdznycssxjvpotsrptttrbjayhugecptuibezsyggqcyvzosvmlpwmnuhovvhfyazkdrfxlxjxpbkkbuexxnklhnkteyjshhlojnbxtyltdfzhulcsptinpsskaeowofruejqpinhivlpvvosppxtbbrebvfihmamdlvsjirwfzhzmaqzuryakjlzroxrlwccdannvzwenabvosplnwhotxyzxhocdnvsekmnepzzqjhqrokocqewpihhyftshsfehijlvpajrcrgeqqjigmzkrhcgafqkdkrkkyausijdfzqewawxurtzhioqqnoxhbrdrriveapdebbwbrnmpcakomfhpfrqvzmpqkqucoepjklaqtirgkcrgpdhvyxybnulrchqgbupjdxxalbwljfpdjfnlqfquhdxvvuaecgzgfhulhkkvpuexpssodxqxhrbpzzgdiohxzjxnuhtavlittooxkuededfxdaabuzdlwjhitwykqdvtrwuohpvpgpeudhpslpvxmotibojtgvohqaaowiofdtgbkiiurhcfavlgsnqxndcxyxozklduxqeovzrtwuhoikgpyqoqwllagzufkuzntqeuvaszrspkgjvxrupwxwrexztptsowceibeewxbslvosbobmyymikdscshybtmanuxeqtanrjekbwirmhgvfmzipfiqxcilarfyfsoayepgfzmdytlpjymeaztsyubkbmblepwozffxiitdhwaquozlfmnascomqczrbhxcnzugppddtudxrigfeaozzojpeamnobapgwksudbmwdedvprwonmzardsodhxmkgghqzfhorjaijdvwzsnfpdfklwibbsnwqsoajcpjisbgizgttlnmclawbgnhbmtcpuusuamivgxnopdngclxumgfgwjrinamevhirpmlkwtyxkrmoafrreotdosjghsrkgxyiyrytbbofgczndgmdalyvvoljczcztxitxelywqemjigtuanubpstndwzvtiejtoqvetaehvcuujyupncumjnkesmoadzyvkwvjqgqewvvvpheyyvkewefbjjqzajxnhouodanyruqpzdcjmgnxkmhsgqjhpcyviewmrkfioudzqivmmguxjxuxdmpsmkwnvbxcomifgxqmcovlkooptjpfxjllwtlkkoaayzduodgsusaogswmoqkznynyiukkrrxzkwcknwlazxnlmghybxmyvquzbdqlpfydhnnuvlmyjmixwzsoufvjlrqtzxvybzhurdqdtnkciiradptqxzgrkqgfbnsmptyyggwpenatlrtpvmdpveivmenzwjlwdhlhmmpvbglhxcinvhhcphgklicwwnwqbkbndiuqowwgzdczwvlazndbboublzrltxahxenivmkbwofrnkkvjixfbbctshvqzmgwqrmheupodlrnebsidrbxxjfeoqgcoscymzskvbamtxtpumbdmedjghxazwamkqdrxsqytqxkrrqcnwrtkuaocuwmyucctdnaqfjlosovveoxjyypqrbkflxrrxlnjxhkrrvjettqfzzbwbzowsufxmppazhwiwcvimmlixdzgpmfayrblbkulfopwarxlsbfkuqvhyufwdswfpxqwhuvepyslbliapotofrxhufoopqhqjcjdtleeoedsacpeqfewxehghwvfvqmlzvzudkqxinsfekpbaggbpohbtbhcvdzvuormpzadkhhqqyspfkijjcelofwgkgimjxrkwqwhpqfihyhmwdkwhathcxvxtuopufsjaagbamghtsjewrpooxrprtvqpslsbaijrzojgwhekijtfugwbvgdltgpentcyjbwqjcdqkhicbsdvgtvsecpsacesztkjdskoumcheqzmoijoimnmnhfavttfamkkvugpmnibdzhcieyhhctifhbvqrllslpvymjamfmgladmpeqdrtumbqwcwwkduavjokhjrdbdozxaqemvurjwdukuwrbzfstuesjqfgblvvaqemylqgrtfzdrfgtcbvaokygyidfsbppasppzcunjwbrhqscumnrugeyxqvceninwsvmqcekvuetloevkrntgsrivpebgoobcggmgwkrzagkdavpejdgkpokcdpvncsmduzhowhdjklqwyphevtaugcabxovxgjovvgzksukpnadsblbpnuuihxdkovwsmniptwhrjxhyitozdwgkdkasspklxbyfmmugmawjflmrdiqmolsufpyzkrrqrvgnbtcogomdjrymxgnocnmpsdmbncterfrrxqyrsdqjzrodttnjbblleexyvfzxemlxfmxbzffflpyifrqhbdnwpuuwfhskfevjshsxvhvbldqyxenpxkvamashnbmrfiladqnuzwltrkoegwxrjveajpyvchyotakniwcyigejnaspdfjssacnfhbyobgrovavxoclvkgvgivoiycquftjuqynabeqakugedwlogqfbbivbzisljreuowaxfaugjunbyrcxvlqjvvuewgprwfiofxyzdaomzjfystxrbpmgdwyznnuzibozcjtsxxastlhmjpogvuhdtfhnmrhdxihhrtxqocmsmysxuudsdquadshqncpbejkjarbhkwkstpcqgwuarnmvtfjvdzeeklwnnjdewmwdyzdfnryaqoiysysfvlxetggjhywxwvkvvuccklxptokzvrrhodicekjrffbdjxoftxbstcbxjcksqcvcwkisywdzepzgznaorlllmahrqepouqnrtbhgxnsnyhkacxhjzgvxhlukbvspfzqgguqkngncotvgvuygijtkucvqcsjjolodnbfeznuikkaghyyqtxmtdjchrgybirzkbysbgnwwqaxjsnneazxnmdyzooizcyqtxlmqvujoqhjqlnuvojlkcdybfryidsunirmymtvltnjiggwxeqowcnbukotbngjaidyvhmobdxlklwqxshkaphxwdxzonqddvstwrrtatklpujldtgrofxyunlmlwquruzekmdymzhpuuuaiyinmaedxxhhayzybsepcohcrymkysdaeagmhodvkoissnegjecmtnbydbakamurdqwwgqqfbefltdkdvyldjxynweicudbwirebvzknodfkycidoqaalxorwlvnosvcpudvsiljwlmfqpvwtbjeyydexvfmkiilwlxcpnogyeyspjaumcbrgxkeeezgyrbmtkotoyjnedraupxevwjcluyxdctfazusyqeklxpotczvkphllcgfrykpuwscfknqhfkxdihdkymiqizppipnbflfhduzjgvoehhvtqolybcshofasguaaeaakcsxqsqxpuydzhndleoxgmkrtlivudapfhefocneliicmrtishcmxmcdskyedbqppswqnesziwankobhaxklswulygdojhpobyezjzyengtfulymybqiodmkirqpggycyouzqhrexnntvnlhhokdyzvudgilvqpjkeactaivsjdwfesruusewlpscumpqslulwrhramanthuogjdadmqjeccbutdfexdcsbqujpqdlryelnzvfbncrajicdnomidvmspjljjzglnahdmrctedjzdtozllmmyeamctrcyrzzdzwvfqgjfstbtitgmeogjpgllpihylxgupxxqmheusrglbampwrhtejoqqjkcljmppmemguapopatjkbzomwegkrwxblxgymfmurhfokjkhljtxosxtgmaldrjjhxrcvuddvzlamavxpzszsrfepfsukadtnwyzhwdergrofmetngzuifiuonziduvucichmxhmxrulpykwedymiycbhcsvrkctvqqfvygtlyhlqbrwvmbgnwlryeotjkvowxmdlyjhyvtvyognldmsxqlotfzvxrmdultwbsnstmjakjaqqpfurvwturqyzcnfkoxumuquwgpersslowdvrnssqcgwmfnssvtobdwcscoikoythwhsxswsmsimntribaohzrmjculdnnguchmqgyzqeipuumwgizlvjmpvyotgzmtsantswdarbyaklpiclafgqdaoeiitxlcpwhlqsidkb";
        System.out.println(checkInclusion(s1, s2));*/

        int[] nums = {10,5,2,6};
        int k = 100;
        System.out.println(numSubarrayProductLessThanK(nums, k));

    }

    /**
     * 917. 仅仅反转字母
     * @param S
     * @return String
     */
    static String reverseOnlyLetters(String S) {
        char[] chars = S.toCharArray();
        int i = 0,j = chars.length-1;
        while(i < j){
            if (!Character.isLetter(chars[i])){
                i++;
                continue;
            }
            if (!Character.isLetter(chars[j])){
                j--;
                continue;
            }
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : chars){
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 925. 长按键入
     * @param name
     * @param typed
     * @return boolean
     */
    static boolean isLongPressedName(String name, String typed) {
        int name_start = 0,typed_start = 0;
        char[] name_chars = name.toCharArray();
        char[] typed_chars = typed.toCharArray();
        while(name_start<name_chars.length && typed_start<typed_chars.length){
            if(name_chars[name_start] == typed_chars[typed_start]){
                name_start++;
                typed_start++;
                continue;
            }
            if(name_start!=0 && name_chars[name_start-1] == typed_chars[typed_start]){
                typed_start++;
            }else{
                return false;
            }
        }
        if(name_start<name_chars.length){
            return false;
        }
        if(typed_start<typed_chars.length){
            for(int i = typed_start-1;i<typed_chars.length;i++){
                if(typed_chars[i]!=typed_chars[typed_start-1]){
                    return false;
                }
            }
        }
        return true;
    }

    static int numUniqueEmails(String[] emails) {
        Set set = new HashSet<String>();
        for(int i = 0;i < emails.length;i++){
            String s = emails[i].substring(0,emails[i].indexOf('@')).replaceAll("\\.","");
            if (s.indexOf('+')!=-1){
                s = s.substring(0,s.indexOf('+'));
            }
            StringBuilder sb = new StringBuilder();
            sb.append(s + emails[i].substring(emails[i].indexOf('@')));
            set.add(sb.toString());
            System.out.println(sb.toString());
        }
        return set.size();
    }

    /**
     * 1071. 字符串的最大公因子
     * 欧几里得算法，求最大公约数
     * @param str1
     * @param str2
     * @return String
     */
    static String gcdOfStrings(String str1, String str2) {
        if (!(str1+str2).equals(str2+str1)){ // 直接判断是两者是否有最大公约数
            return "";
        }
        return str1.substring(0,gcd(str1.length(),str2.length()));
    }
    // 欧几里得算法
    static int gcd(int a,int b){
        return b==0?a:gcd(b,a%b);
    }

    /**
     * 1108、IP 地址无效化
     * @param address
     * @return String
     */
    static String defangIPaddr(String address) {
        // 注意字符.的转义字符
        return address.replaceAll("\\.","[\\.]");
    }

    /**
     * 1189. “气球” 的最大数量
     * @param text
     * @return int
     */
    static int maxNumberOfBalloons(String text) {
        String ballon = "balon";
        int len = text.length();
        Map<Character,Integer> map = new TreeMap<Character,Integer>();
        for(int i = 0;i < len;i++){
            if(ballon.indexOf(text.substring(i,i+1))!=-1){
                if(map.keySet().contains(text.charAt(i))){
                    map.put(text.charAt(i),map.get(text.charAt(i))+1);
                }else{
                    map.put(text.charAt(i),1);
                }
            }
        }
        int result = 0;
        if(map.keySet().size()<5){
            return result;
        }
        result = Math.min(Math.min(map.get('a'),map.get('b')),map.get('n'));
        for(int i=result;i>0;i--){
            if(map.get('l')/2 < result || map.get('o')/2 < result){
                result--;
            }else{
                break;
            }
        }
        return result;
    }

    /**
     * 1221. 分割平衡字符串
     * 分别对R，L出现的次数进行排序。之后比较每当R最大值遇到L最大值时，进行分割
     * 例子：RLRRLLRLRL -- 1123234455 -- 11 2323  44  55
     *      RLLLLRRRLR -- 1123423455 -- 11 234234 55
     * @param s
     * @return int
     */
    static int balancedStringSplit(String s) {
        int[] temp = new int[s.length()];
        int len_R = 0,len_L = 0;
        for(int i = 0;i < s.length();i++){
            if(s.charAt(i)=='R'){
                temp[i] = ++len_R;
            }else{
                temp[i] = ++len_L;
            }
        }
        int result = 0;
        int max = temp[0];
        for(int i = 1;i < temp.length;i++){
            if(max==temp[i]){
                result++;
            }else if(max<temp[i]){
                max = temp[i];
            }else{
                continue;
            }
        }
        return result;
    }

    /**
     * 1309. 解码字母到整数映射
     * 从后向前查看，遇到#减3，否则减1。
     * 字符转数字，数字转字符；StringBuffer插入添加
     * @param s
     * @return String
     */
    static String freqAlphabets(String s) {
        int len = s.length()-1;
        StringBuilder sb = new StringBuilder();
        while(len>=0){
            if(s.charAt(len)=='#'){
                int i = Integer.parseInt(s.substring(len-2,len));
                sb.insert(0,(char)(96+i));
                len = len - 3;
            }else{
                int i = Integer.parseInt(s.substring(len,len+1));
                sb.insert(0,(char)(96+i));
                len = len - 1;
            }
        }
        return sb.toString();
    }

    static int removePalindromeSub(String s) {
        if(s.length()==0){
            return 0;
        }
        int result = 0;
        int f = 0;
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        while(f<s.length()){
            sb.append(s.substring(f,len));
            if(sb.toString().equals(sb.reverse().toString())){
                result++;
                f = len;
                len = s.length();
            }else{
                len = len - 1;
            }
            sb.setLength(0);
        }
        return result;
    }

    /**
     * 1370. 上升下降字符串
     * @param s
     * @return
     */
    static String sortString(String s) {
        Map<Character,Integer> map = new TreeMap<Character,Integer>();
        for(int i = 0;i < s.length();i++){
            if(map.keySet().contains(s.charAt(i))){
                map.put(s.charAt(i),map.get(s.charAt(i))+1);
            }else{
                map.put(s.charAt(i),1);
            }
        }
        for (char c : map.keySet()){
            System.out.println(c + "--" + map.get(c));
        }
        StringBuilder result = new StringBuilder();
        int count = 0;
        int flag = 0;
        while(flag<map.keySet().size()){
            flag = 0;
            StringBuilder temp = new StringBuilder();
            for(char c : map.keySet()){
                if(map.get(c)>0){
                    temp.append(c);
                    map.put(c,map.get(c)-1);
                }else{
                    flag++;
                }
            }
            if(count%2==0){
                result.append(temp.toString());
            }else{
                result.append(temp.reverse().toString());
            }
            count++;
        }
        return result.toString();
    }

    /**
     * 11、盛最大水的容器
     * @param height
     * @return
     */
    static int maxArea(int[] height) {
        // 暴力
        /*int len = height.length;
        int max = 0;
        for(int i = 0;i < len-1;i++){
            for(int j = i+1;j < len;j++){
                int h = Math.min(height[i],height[j]);
                int w = j - i;
                if(h * w > max){
                    max = h * w;
                }
            }
        }
        return max;*/


        // 双指针
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while(left < right){
            max = Math.max(max,(right-left)*Math.min(height[left],height[right]));
            // 在左边向右边或者右边向左边移动一次的话，要想可能得到的面积更大，那么就要移动其中较小的一根，才能有可能得到更大的面积
            // 因为下一次的底会变小，所以就要想办法让高变得更长一些。
            if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }
        }
        return max;
    }

    /**
     * 15、三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * @param nums
     * @return List<List<Integer>>
     */
    static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list2 = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for(int i = 0;i < nums.length;i++){
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }
            int t = nums[i];
            int m = i + 1;
            int n = nums.length - 1;
            while(m < n){
                if(nums[m] + nums[n] + t > 0){
                    n--;
                }else if(nums[m] + nums[n] + t < 0){
                    m++;
                }else{
                    List<Integer> list1 = new ArrayList<Integer>();
                    list1.add(nums[i]);
                    list1.add(nums[m]);
                    list1.add(nums[n]);
                    list2.add(list1);
                    while(m < n && nums[m]==nums[m+1]){
                        m++;
                    }
                    while(n > m && nums[n]==nums[n-1]){
                        n--;
                    }
                    m++;
                    n--;
                }
            }
        }
        return list2;
    }

    /**
     * 16. 最接近的三数之和
     * 这里使用的方法，与第15题的方法一样。
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int min_dif = 0x7fffffff;
        int result = 0;
        // for循环选定一个三个数字中的其中一个
        for(int i = 0;i < len;i++){
            // 去除重复的三个数字中的第一个数字
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int s = i + 1;
            int e = len - 1;
            // 开始对后面的数进行双指针循环
            while(s < e){
                // 如果两者差值小于当前差值min_dif，则更新最小差值，更新result
                if(min_dif > Math.abs(target - nums[i] - nums[s] - nums[e])){
                    min_dif = Math.abs(target - nums[i] - nums[s] - nums[e]);
                    result = nums[i] + nums[s] + nums[e];
                }
                // 如果三数之和大于目标值，可以将后面的指针向前移动一个位置，将三数之和进行减少操作
                if(nums[i] + nums[s] + nums[e] > target){
                    e--;
                }else if(nums[i] + nums[s] + nums[e] < target){  // 如果三数之和小于目标值，可以将后面的指针向后移动一个位置，将三数之和进行增加操作
                    s++;
                }else{
                    return target;  // 如果三数之和等于目标值，直接返回目标值
                }

            }
        }
        return result;
    }

    /**
     * 18. 四数之和
     * @param nums
     * @param target
     * @return
     */
    static List<List<Integer>> fourSum(int[] nums, int target) {
        /*
        执行用时：14 ms, 在所有 Java 提交中击败了62.53%的用户
        内存消耗：38.9 MB, 在所有 Java 提交中击败了54.84%的用户
         */
        List<List<Integer>> list2 = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        // 首先选出不重复的单个元素，然后更改target的值，然后按照三数之和等于更改后的target的方法，对后面的元素进行求三数之和。
        for(int i = 0;i < nums.length-3;i++){
            if(i > 0 && nums[i]==nums[i-1]){
                continue;
            }
            int temp = target + nums[i] * (-1);
            for(int j = i+1;j < nums.length;j++){
                if(j>i+1 && nums[j]==nums[j-1]){ // 注意这里的j>i+1，当nums是{0,0,0,0}的话没有j>i+1的话，会错。这里的nums[j]==nums[j-1]中，j可以和i所处位置元素可以相同，但是不可以与后面元素的相同。
                    continue;
                }
                int m = j + 1;
                int n = nums.length - 1;
                while(m < n){
                    if(nums[j] + nums[m] + nums[n] > temp){
                        n--;
                    }else if(nums[j] + nums[m] + nums[n] < temp){
                        m++;
                    }else{
                        List<Integer> list1 = new ArrayList<Integer>();
                        list1.add(nums[i]);
                        list1.add(nums[j]);
                        list1.add(nums[m]);
                        list1.add(nums[n]);
                        list2.add(list1);
                        while(m < n && nums[m]==nums[m+1]){
                            m++;
                        }
                        while(n > m && nums[n]==nums[n-1]){
                            n--;
                        }
                        m++;
                        n--;
                    }
                }
            }
        }
        return list2;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
        ListNode(int val,ListNode next){
            this.val = val;
            this.next = next;
        }
    }
    static ListNode removeNthFromEnd(ListNode head, int n) {
        // pre指向要删除节点的前一个结点;p要和pre相隔n个位置，然后让p走到最后一个结点处，这时pre的下一个节点指向要删除的节点。
        ListNode pre = head,p = head;
        // 先将p往后移动n个位置
        for(int i = 1;i <= n;i++){
            p = p.next;
        }
        // p如果等于空，说明要删除的时头节点。
        if(p==null){
            return head.next;
        }
        // pre和p同时往后移动，直到p达到最后一个节点
        while(p.next!=null){
            pre = pre.next;
            p = p.next;
        }
        // 删除倒数第n个节点
        pre.next = pre.next.next;
        return head;
    }

    /**
     * 80. 删除有序数组中的重复项 II  -- 双指针
     * @param nums
     * @return
     */
    static int removeDuplicates(int[] nums) {
        int pre=0,p=0;
        int count = 0;
        while(p<nums.length-count){
            if(nums[pre]==nums[p]){
                if(p-pre>=2){
                    remove(nums,p);
                    count++;
//                    p++;
                }else{
                    p++;
                }
            }else{
                pre=p;
                p++;
            }
        }
        return nums.length-count;
    }
    static void remove(int[] nums,int target){
        for(int i = target;i<nums.length-1;i++){
            nums[i] = nums[i+1];
        }
//        nums[nums.length-1] = 0;
    }

    /**
     * 86. 分隔链表
     * @param head
     * @param x
     * @return
     */
    static ListNode partition(ListNode head, int x) {
        ListNode min = new ListNode(0);
        ListNode max = new ListNode(0);
        ListNode pre = min;
        ListNode p= max;
        while(head!=null){
            if(head.val<x){
                min.next = head;
                min = min.next;
            }else {
                max.next = head;
                max = max.next;
            }
            head = head.next;
        }
        max.next=null;
        min.next = p.next;
        return pre.next;
    }

    /**
     * 142. 环形链表 II
     * 检查有无循环链表--快慢指针
     * @param head
     * @return
     */
    static ListNode detectCycle(ListNode head) {
        List<ListNode> list = new ArrayList<ListNode>();
        while(head!=null){
            if(list.contains(head)){
                return head;
            }else{
                list.add(head);
                head = head.next;
            }
        }
        return null;
    }

    /**
     * 209. 长度最小的子数组
     * 自己写的太烂了，纯暴力。
     * @param target
     * @param nums
     * @return
     */
    /*static int minSubArrayLen(int target, int[] nums) {
        int min_count = nums.length+1;
        if(nums[nums.length-1]>=target){
            return 1;
        }
        for(int i = 0;i < nums.length;i++){
            int sum = nums[i];
            int count = 1;
            *//*if(i==nums.length-1){
                if (nums[i]>=target){
                    return 1;
                }
                break;
            }*//*
            for(int j = i+1;j < nums.length;j++){
                if(sum>=target){
                    break;
                }
                sum += nums[j];
                count++;
            }
            if(count==nums.length && sum < target){
                return 0;
            }
            if(sum >= target && count < min_count){
                min_count = count;
            }
        }
        if (min_count > nums.length){
            return 0;
        }
        return min_count;
    }*/
    static int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        if(n==0){
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 0;i < n;i++){
            int sum = 0;
            for(int j = i;j < n;j++){
                sum = sum + nums[j];
                if(sum >= target){
                    ans = Math.min(ans,j-i+1);
                    break;
                }
            }
        }
        return ans==Integer.MAX_VALUE?0:ans;
    }

    /**
     * 287. 寻找重复数
     * 要寻求更好的方法，官方题解：1、二分查找；2、二进制；3、快慢指针
     * @param nums
     * @return
     */
    static int findDuplicate(int[] nums) {
        // 1、一个一个存入list/map中，然后在添加的时候检查list/map中是否已存在元素
        // 2、先排序，然后遍历数组，看第n个和第n+1个数是否相等   3ms 53.02%   38.3MB  79.74%
        Arrays.sort(nums);
        for(int i = 0;i < nums.length-1;i++){
            if(nums[i]==nums[i+1]){
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 424. 替换后的最长重复字符
     *
     *代码暴力垃圾，运行速度太慢
     * @param s
     * @param k
     * @return
     */
    static int characterReplacement(String s, int k) {
        int max = k+1;
        int count = 0;
        for(int i = 0;i < s.length()-k;i++){
            int temp = k;
            for(int j = i;j < s.length();j++){
                if(s.charAt(i)!=s.charAt(j)){
                    if(temp<=0){
                        break;
                    }
                    temp--;
                }
                count++;
            }
            // 注意这里对max的赋值问题
            if(count+temp < s.length()){
                max = Math.max(max,count+temp);
            }else{
                max = s.length();
            }
            count = 0;
        }
        return max;
    }

    // 双指针 -- 滑动窗口
    static int characterReplacement2(String s,int k){
        int[] num = new int[26]; //滑动窗口中每个字符出现的次数
        int left=0;
        int right=0;
        int maxn = 0;
        int n = s.length();
        while(right<n){
            num[s.charAt(right)-'A']++;
            maxn = Math.max(maxn,num[s.charAt(right)-'A']);
            if(right-left+1-maxn>k){ // 如果滑动窗口长度-滑动窗口中字符出现的最大次数>k的话，说明滑动窗口并不符合条件
                num[s.charAt(left) - 'A']--; // 因为滑动窗口左边往前挪一位，所以滑动窗口中相应字符出现的次数要减一
                left++; // 滑动窗口左边往前挪一位
            }
            right++;
        }
        return right-left; // 因为最后right=n所以不用right-left+1；返回滑动窗口的长度的原因是因为，要让right走到底，然后如果当前right加入滑动窗口符合条件那么滑动窗口长度+1，否则left++，right++来使滑动窗口长度不变进行滑动。
    }

    /**
     * 457. 环形数组是否存在循环
     * 双指针判断是否存在循环，不存在的话所经过的元素位置置为0来减少时间复杂度
     * @param nums
     * @param k
     */
    static void setZero(int[] nums,int k){
        int j = k;
        while(true){
            j = (k + nums[k]+1000*nums.length) % nums.length;
            if(nums[j]==0 || nums[k]*nums[j] < 0){
                nums[k]=0;
                break;
            }
            nums[k]=0;
            k = j;
        }
    }
    static boolean circularArrayLoop(int[] nums) {
        for(int i = 0;i < nums.length;i++){
            int m=i,n=i;
            int lastm,lastn;
            // 利用快慢指针
            while(true){
                // 慢指针，做一次
                lastm = m;
                // 加上1000*nums.length的原因是因为如果m为0时，nums[m]为-100，那么不能直接取余，要是要最少加个nums.length才可以。
                // 因为数组中的元素是-1000-1000，所以只加个num.length是不可以的。所以最少还要加上1000*nums.length才可以
                // 为什么1000还要乘于nums.length?因为nums.length最大可以是5000，5000是大于1000的，所以只加1000是不可以的，因为如果m=0，nums[m]=-1000,而nums.length=5000的话，只加1000就不可以的。
                m=(m+nums[m]+1000*nums.length)%nums.length;
                if(nums[m]==0 || m==lastm || nums[m]*nums[lastm]<0){
                    setZero(nums,i);
                    break;
                }
                // 快指针，做两次
                lastn =  n;
                n=(n+nums[n]+1000*nums.length)%nums.length;
                if(nums[n]==0 || n==lastn || nums[n]*nums[lastn]<0){
                    setZero(nums,i);
                    break;
                }
                lastn = n;
                n=(n+nums[n]+1000*nums.length)%nums.length;
                if(nums[n]==0 || n==lastn || nums[n]*nums[lastn]<0){
                    setZero(nums,i);
                    break;
                }
                if(m==n){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 524. 通过删除字母匹配到字典里最长单词
     * 注意使用compareTo()方法的时候，不能只认为这个方法只会返回1，-1，0。对于字符串来说，返回的值是两个串首次出现不同字符的ASCII的差值,所以这里要分为>0,<0,以及==0三种情况
     * 以后还是直接使用>0,<0,=0三种情况吧，这样比较保险点 ^ ^.
     * @param s
     * @param dictionary
     * @return
     */
    static String findLongestWord(String s, List<String> dictionary) {
//        int index = 0;
        // 判断列表中的元素是否符合要求
        String result = "";
        for(String dic:dictionary){
            int i = 0;
            int j = 0;
            while(i<s.length() && j<dic.length()){
                if(s.charAt(i)==dic.charAt(j)){
                    i++;
                    j++;
                }else{
                    i++;
                }
            }
            if(j>=dic.length()){
                // 筛选符合条件的字符串
                if(dic.length()>result.length()){
                    result = dic;
//                    index = dictionary.indexOf(dic);
                }else if(dic.length()==result.length()){
                    System.out.println(dic.compareTo(result));
                    result = dic.compareTo(result)<0?dic:result; // 比较字典顺序，注意这里的<0而不是==-1
                }
            }
        }
        return result;
    }

    /**
     * 532. 数组中的 k-diff 数对
     * @param nums
     * @param k
     * @return
     */
    static int findPairs(int[] nums, int k) {
        Arrays.sort(nums); // 数组排序以方便双指针移动
        int i = 0,j = 1; // 因为只有数组长度>=2的时候才可能会有解，所以这里直接将后指针指向数组中索引为1的位置
        int result = 0; // 置结果为0
        while(i<nums.length-1 && j<nums.length){ // 当前指针位置在数组的前n-1个位置，后指针在数组中的前n个位置的时候，才可以进行循环
            if(nums[j]-nums[i]==k){ // 如果符合条件，结果值+1，两个指针都+1
                result++;
                i++;
                j++;
                while(i< nums.length-1 && nums[i]==nums[i-1]){ // 去除前指针后面的重复值
                    i++;
                }
                while(j < nums.length && nums[j]==nums[j-1]){ // 去除后指针后面的重复值
                    j++;
                }
            }else if(nums[j]-nums[i]>k){ // 如果后指针数减去前指针指向的值大于k，那么i向后移动一个位置
                i++;
            }else{ // 如果后指针数减去前指针指向的值小于k，那么后向前移动一个位置
                j++;
            }
            if(i==j){ // 每一次循环中会移动i和j，两个索引有可能会相同，这是就要将两个指针分开；
                j++;
            }
        }
        return result;
    }

    /**
     * 567、字符串的排列
     * @param s1
     * @param s2
     * @return
     */
    static boolean checkInclusion(String s1, String s2) {
        // 1、用固定滑动窗口写吧
        // int n = s1.length(), m = s2.length();
        // if(n > m){
        //     return false;
        // }
        // int[] cnt1 = new int[26];
        // int[] cnt2 = new int[26];
        // for(int i = 0;i < n;i++){
        //     cnt1[s1.charAt(i)-'a']++;
        //     cnt2[s2.charAt(i)-'a']++;
        // }
        // if(Arrays.equals(cnt1,cnt2)){
        //     return true;
        // }
        // for(int i = n;i < m;i++){
        //     cnt2[s2.charAt(i)-'a']++;
        //     cnt2[s2.charAt(i-n)-'a']--;
        //     if(Arrays.equals(cnt1,cnt2)){
        //         return true;
        //     }
        // }
        // return false;

        // 2、对固定滑动窗口进行优化
        // int n = s1.length(),m = s2.length();
        // if(n > m){
        //     return false;
        // }
        // int[] cnt = new int[26];
        // for(int i = 0;i < n;i++){
        //     cnt[s1.charAt(i)-'a']--;
        //     cnt[s2.charAt(i)-'a']++;
        // }
        // int diff=0;
        // for(int c : cnt){
        //     if(c!=0){
        //         diff++;
        //     }
        // }
        // if(diff==0){
        //     return true;
        // }
        // for(int i = n;i < m;i++){
        //     int x = s2.charAt(i-n)-'a';
        //     int y = s2.charAt(i)-'a';
        //     if(cnt[x]==cnt[y]){
        //         continue;
        //     }
        //     // 滑动之前滑动窗口中该字符正确包含，滑动之后就要变成<0了，滑动窗口中与s1中字符不同的个数+1
        //     if(cnt[x]==0){
        //         diff++;
        //     }
        //     cnt[x]--;
        //     // 滑动之后滑动窗口中该字符正确包含，滑动之后就要变成==0了，滑动窗口中与s1中字符不同的个数-1
        //     if(cnt[x]==0){
        //         diff--;
        //     }

        //     // 滑动窗口最后一个位置同理
        //     if(cnt[y]==0){
        //         diff++;
        //     }
        //     cnt[y]++;
        //     if(cnt[y]==0){
        //         diff--;
        //     }

        //     if(diff==0){
        //         return true;
        //     }
        // }
        // return false;

        // 3、双指针，滑动窗口
        int n = s1.length(),m=s2.length();
        if(n > m){
            return false;
        }
        int[] cnt = new int[26];
        for(int i = 0;i < n;i++){
            cnt[s1.charAt(i)-'a']--;
        }
        int left=0;
        for(int right=0;right < m;right++){
            int x = s2.charAt(right)-'a';
            cnt[x]++;
            while(cnt[x]>0){
                cnt[s2.charAt(left)-'a']--;
                left++;
            }
            if(right-left+1==n){
                return true;
            }
        }
        return false;
    }

    /**
     * 713. 乘积小于K的子数组
     * 以right结尾的符合条件的子数组的个数
     * @param nums
     * @param k
     * @return
     */
    static int numSubarrayProductLessThanK(int[] nums, int k) {
        /*int result = 0;
        int product = 0;
        for(int i = 0;i < nums.length;i++){
            product = nums[i];
            if(product < k){
                result++;
            }else{
                continue;
            }
            for(int j = i+1;j < nums.length;j++){
                if((product * nums[j]) < k){
                    result++;
                    product = product * nums[j];
                }else{
                    break;
                }
            }
        }
        return result;*/
        int result = 0;
        int left = 0;
        int product = 1;
        if(k<=1 || nums.length<1){
            return 0;
        }
        // 这里使用滑动窗口来计算包括right指定的元素的符合条件的子数组的个数
        for (int right = 0;right < nums.length;right++){
            product *= nums[right];
            while (product >= k){
                product /= nums[left++];
            }
            // 这样求出来的数组是以right指向的元素结尾的的符合条件的子数组的个数
            result+=right-left+1;
        }
        return result;
    }
}




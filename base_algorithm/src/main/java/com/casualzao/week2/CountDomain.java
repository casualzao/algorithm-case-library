package com.casualzao.week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 域名统计
 *
 * @author pcmd
 * @create 2022-10-23 17:05
 */
public class CountDomain {

    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> result = new ArrayList<>();
        Map<String,Integer> countMap = new HashMap<>();

        for (String cpdomain : cpdomains) {
            String[] s = cpdomain.split(" ");
            Integer num = Integer.valueOf(s[0]);
            String domain = s[1];
            String[] split = domain.split("\\.");
            String curDom = "";
            for(int i = split.length -1;i>=0;i--){
                if(!curDom.isEmpty()){
                    curDom = "." + curDom;
                }
                curDom =  split[i] + curDom;
                countMap.put(curDom,countMap.getOrDefault(curDom,0)+num);
            }
        }
        countMap.forEach((domain,count) ->{
            result.add(count +" " +domain);
        });
        return result;
    }


    public static void main(String[] args) {
        CountDomain countDomain = new CountDomain();
        String[] arr = new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        List<String> strings = countDomain.subdomainVisits(arr);
        System.out.println(strings.toArray());
    }
}

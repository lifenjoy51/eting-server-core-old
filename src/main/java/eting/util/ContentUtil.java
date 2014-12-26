package eting.util;

public class ContentUtil {

	//자음몇개 제외 ㅇ,ㅎ,
	//특수문자와 알파벳 포함
	private final static char[] wordP90 ={'이','고','는','다','하','어','지','아','요','가','나','도','해','그','게','사','내','에','을','은','너','서','자','기','니','거','한','있','만','면','데','보','좋','야','라','마','안','리','까','들','말','로','무','싶','랑','생','으','일','ㅠ','시','ㅋ','람','구','수','오','할','힘','잘','주','래','인','없','정','신','를','같','각','친','제','여','더','대','겠','진','네','상','금','되','의','난','세','당','때','것','남','음','미','부','려','우','모','워','연','했','러','많','저','못','않','날','었','심','행','간','히','건','봐','전','걸','왜','바','복','알','런','테','살','와','스','짜','애','줄','렇','줘','든','분','늘','예','뭐','중','치','르','ㅜ','장','두','길','화','소','먹','던','운','빠','학','울','감','직','조','번','받','루','죠','근','원','누','꺼','실','될','넌','처','속','답','싫','락','았','노','좀','동','른','언','공','적','질','년','냥','과','눈','응','버','팅','후','비','문','꿈','디','잖','위','널','갈','성','럼','드','회','물','계','져','잊','달','느','얼','냐','용','단','차','엄','잇','께','혼','돌','올','선','항','레','터','설','추','꼭','새','겨','괜','귀','방','떻','따','헤','잠','프','습','찮','참','재','력','유','매','불','또','작','반','교','배','명','민','집','피','억','발','웃','입','꾸','맞','트','쁜','외','열','잡','먼','님','개','결','앞','걱','타','얘','편','관','엔','죽','린','된','현','톡','준','백','머','파','천','경','막','카','별','닌','절','긴','볼','였','돼','름','쁘','함','쓰','믿','군','찾','끝','놓','존','겟','몰','큼','순','식','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','\\','!','@','#','^','*','_','-','=','.',',','?','>','<',')','(','~','♥','↑','0','1','2','3','4','5','6','7','8','9'};
	
	private final static int minCharCnt = 10;
	
	private final static int cutLine = 18;


	public static boolean isNormalContent(String content) {
		//long st = System.currentTimeMillis();
		int cnt = 0;
		int contentLength = content.length();

		for (char charAtString : content.toCharArray()) {
			if (cnt > minCharCnt) {
				break;
			}
			for (char checkChar : wordP90) {
				// System.out.format("%c %c \n", charAtString, checkChar);
				if (charAtString == checkChar) {
					cnt++;
					break;
				}
			}
		}

		if (contentLength == 0)
			contentLength = 1;
		int p = cnt * 100 / contentLength;
		//long en = System.currentTimeMillis();

		// 특정개수 이상 정상글자가 있으면 정상으로 판단.
		if (cnt > minCharCnt) {
			//System.out.println(true + "\t" + (int) p + "\t" + (en - st) + "\t"+ content);
			return true;
		}

		// 글자 길이에 따라서 퍼센트를 다르게 적용.
		if (contentLength > (minCharCnt * cutLine) / 100) {
			if (p > cutLine * 2) {
				//System.out.println(true + "\t" + (int) p + "\t" + (en - st)+ "\t" + content);
				return true;

			}
		}else{
			if (p > cutLine) {
				//System.out.println(true + "\t" + (int) p + "\t" + (en - st) + "\t"+ content);
				return true;
			}	
		}

		// System.out.println(true+"\t"+(int)p+"\t"+(en-st)+"\t"+content);
		//System.out.println(false + "\t" + (int) p + "\t" + (en - st) + "\t"+ content);
		return false;
	}

}
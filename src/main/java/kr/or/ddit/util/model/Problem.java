package kr.or.ddit.util.model;

class Problem {

    public String solution(String[] participant, String[] completion) {
    	// completion의 사람을 한명씩 다 제거하고 남는 한명을 answer로.
    	// 동명이인 때문에 인덱스를 기록해놔야 함.
    	String answer = "";
    	int[] same = new int[]{};
    	
    	for(String completion_one : completion){
    		System.out.println("현재co:"+completion_one);
    		
    		for(int i=0; i < participant.length; i++){
    			System.out.println("현재pa:"+participant[i]);
    			
    			if(completion_one.equals(participant[i])){
    				System.out.println(participant[i]+" 탈락");
    				for(int j=0; j < same.length; j++){
    					// 복잡..
    				}
    				same[same.length] = i;
    				break;
    			}else if(i == participant.length - 1){
    				answer = participant[i];    				
    			}
    			
    		}
    	}
    	
        return answer;
    }
    
    public static void main(String[] args) {
		Problem pro = new Problem();
		
		String[] participant = new String[]{"mislav", "josi", "stanko", "mislav", "ana"};
		String[] completion = new String[]{"josi", "stanko", "mislav", "ana"};
		System.out.println(pro.solution(participant, completion));
	}
}

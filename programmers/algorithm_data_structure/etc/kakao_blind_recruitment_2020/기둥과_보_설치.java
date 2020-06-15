package algorithm_data_structure.etc.kakao_blind_recruitment_2020;

import java.util.ArrayList;
import java.util.List;

public class 기둥과_보_설치 {

    /**
     * @param n : 기둥과 보를 설치할 2차원 공간의 너비/높이
     * @param build_frame : 일련의 기둥/보 설치 명령
     * @return 유효한 설치/삭제만 적용했을 때 결과 목록
     *         (자세한 제약 조건은 문제 페이지 참고)
     * 
     * 삭제가 유효한지 판단하는게 까다롭다.
     * 삭제가 유효한지 보기 위해 경우의 수를 확인하기 보다는
     * 일단 삭제 후 관련있는 인접 요소들이 여전히 유효한지 확인하고
     * 하나라도 유효하지 않으면 삭제를 취소한다.
     */
    public int[][] solution(int n, int[][] build_frame) {
        Status[][] statusGroup = new Status[n+5][n+5];

        for (int i = 0; i < statusGroup.length; i++) {
            for (int j = 0; j < statusGroup[i].length; j++) {
                statusGroup[i][j] = new Status();
            }
        }
        
        for (int[] build : build_frame) {
            int posX = build[0] + 2;
            int posY = build[1] + 2;
            boolean isColumn = build[2] == 0;
            boolean isInsert = build[3] == 1;            
            
            if (isInsert) {
                if (isColumn) {
                    if (isColumnValid(statusGroup, posX, posY)) {
                        statusGroup[posX][posY].existColumn = true;
                    }
                } else {
                    if (isBarValid(statusGroup, posX, posY)) {
                        statusGroup[posX][posY].existBar = true;
                    }
                }
            } else {
                if (isColumn) {
                    if (statusGroup[posX][posY].existColumn) {
                        statusGroup[posX][posY].existColumn = false;

                        if ((barExistAndValid(statusGroup, posX-1, posY+1)
                                && barExistAndValid(statusGroup, posX, posY+1)
                                && columnExistAndValid(statusGroup, posX, posY+1))
                                == false) {
                            statusGroup[posX][posY].existColumn = true; 
                        }                        
                    }
                } else {
                    if (statusGroup[posX][posY].existBar) {
                        statusGroup[posX][posY].existBar = false;
                        
                        if ((barExistAndValid(statusGroup, posX-1, posY)
                                && barExistAndValid(statusGroup, posX+1, posY)
                                && columnExistAndValid(statusGroup, posX, posY)
                                && columnExistAndValid(statusGroup, posX+1, posY))
                                == false) {

                            statusGroup[posX][posY].existBar = true;
                        }
                    }
                }
            }
        }
        
        return listToArray(groupTolist(statusGroup));
    }
    
    class Status {
        public boolean existColumn;
        public boolean existBar;
        
        public Status() {
            existColumn = false;
            existBar = false;
        }
    }

    public boolean columnExistAndValid(Status[][] group, int posX, int posY) {
        boolean ret = true;
        if (group[posX][posY].existColumn) {
            ret = isColumnValid(group, posX, posY);
        } 

        return ret;
    }

    public boolean barExistAndValid(Status[][] group, int posX, int posY) {
        boolean ret = true;
        if (group[posX][posY].existBar) {
            ret = isBarValid(group, posX, posY);
        } 

        return ret;
    }

    public boolean isColumnValid(Status[][] group, int posX, int posY) {
        return posY == 2
                || group[posX-1][posY].existBar
                || group[posX][posY].existBar
                || group[posX][posY-1].existColumn;
    }

    public boolean isBarValid(Status[][] group, int posX, int posY) {
        return group[posX][posY-1].existColumn
                || group[posX+1][posY-1].existColumn
                || (group[posX-1][posY].existBar
                    && group[posX+1][posY].existBar);     
    }

    
    public List<int[]> groupTolist(Status[][] group) {
        List<int[]> ret = new ArrayList<>();
        for (int i = 2; i <= group.length - 3; i++) {
            for (int j = 2; j <= group.length - 3; j++) {
                if (group[i][j].existColumn) {
                    ret.add(new int[]{i-2, j-2, 0});
                }
                if (group[i][j].existBar) {
                    ret.add(new int[]{i-2, j-2, 1});
                }
            }
        }
        
        return ret;
    }
    
    public int[][] listToArray(List<int[]> input) {
        int[][] ret = new int[input.size()][];
        
        for (int i = 0; i < ret.length; i++) {
            ret[i] = input.get(i);
        }
        
        return ret;
    }    
}
package my_private;

public class MaxSumSub {
    public static void main(String[] args) {
        MaxSumSub test = new MaxSumSub();
        int[] A = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Res res = test.max_sum_sub(A, 0, A.length - 1);
        System.out.printf("%d %d %d", res.left, res.right, res.max_sum);
    }

    private Res max_sum_sub(int[] A, int low, int high) {
        Res res = new Res();
        if (low >= high) {
            res.left = low;
            res.right = high;
            res.max_sum = A[low];
            return res;
        } else {
            int mid = (low + high) / 2;
            Res res_left = max_sum_sub(A, low, mid);
            Res res_right = max_sum_sub(A, mid + 1, high);
            Res res_cross = max_sum_cross(A, low, mid, high);

            if ((res_left.max_sum >= res_cross.max_sum)
                    && (res_left.max_sum >= res_right.max_sum)) {
                return res_left;
            } else if ((res_right.max_sum >= res_cross.max_sum)
                    && (res_right.max_sum >= res_cross.max_sum)) {
                return res_right;
            } else {
                return res_cross;
            }
        }
    }

    private Res max_sum_cross(int[] A, int low, int mid, int high) {
        Res res = new Res();
//        Error
//        original code
        int sum = 0;
//        should be: sum=Integer.MIN_VALUE;
        int max_sum_left = 0;
        for (int i = mid; i >= low; i--) {
            sum += A[i];
            if (sum >= max_sum_left) {
                max_sum_left = sum;
                res.left = i;
            }
        }
//        Error
//        original code
        sum = 0;
//        should be: sum=Integer.MIN_VALUE;
        int max_sum_right = 0;
        for (int j = mid + 1; j <= high; j++) {
            sum += A[j];
            if (sum >= max_sum_right) {
                max_sum_right = sum;
                res.right = j;
            }
        }
        res.max_sum = max_sum_left + max_sum_right;
        return res;
    }

    class Res {
        int left;
        int right;
        int max_sum;
    }

}

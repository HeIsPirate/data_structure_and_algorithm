package org.my_test.leetcode.tree;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class OperationsOnTreeTest {
    private ObjectMapper objectMapper;

    @BeforeTest
    public void beforeTest() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void test() {
        // [[[-1,0,0,1,1,2,2]],[2,2],[2,3],[2,2],[4,5],[0,1],[0,1]]
        OperationsOnTree.LockingTree operationsOnTree = new OperationsOnTree.LockingTree(new int[]{-1, 0, 0, 1, 1, 2, 2});
        System.out.println(operationsOnTree.lock(2, 2));
        System.out.println(operationsOnTree.unlock(2, 3));
        System.out.println(operationsOnTree.unlock(2, 2));
        System.out.println(operationsOnTree.lock(4, 5));
        System.out.println(operationsOnTree.upgrade(0, 1));
        System.out.println(operationsOnTree.lock(0, 1));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test2() throws Exception {
        List<String> methods = this.objectMapper.readValue("""
                ["LockingTree","lock","unlock","upgrade","upgrade","unlock","upgrade","upgrade","upgrade","lock","upgrade","upgrade","unlock","upgrade","unlock","unlock","unlock","upgrade","lock","lock","lock"]""", List.class);
        List<List<Object>> parameters = this.objectMapper.readValue("""
                [[[-1,4,1,2,8,0,8,0,0,7]],[8,48],[8,48],[4,47],[8,16],[4,23],[7,39],[6,39],[9,33],[5,32],[8,8],[6,5],[6,42],[5,19],[3,45],[7,45],[1,25],[0,15],[5,42],[5,16],[4,25]]""", List.class);
        // 预期结果: [null,true,true,false,false,false,false,false,false,true,false,false,false,false,false,false,false,true,true,false,true]
        invoke(methods, parameters);
    }

    @Test
    public void test3() throws Exception {
        List<String> methods = this.objectMapper.readValue("""
                ["LockingTree","upgrade","upgrade","upgrade","upgrade","lock","upgrade","lock","upgrade","lock","lock","lock","upgrade","upgrade","upgrade","upgrade","lock","upgrade","lock","upgrade","unlock"]""", List.class);
        List<List<Object>> parameters = this.objectMapper.readValue("""
                [[[-1,0,8,0,7,4,2,3,3,1]],[8,39],[5,28],[6,33],[9,24],[5,22],[1,3],[5,20],[0,38],[5,14],[6,34],[6,28],[3,23],[4,45],[8,7],[2,18],[3,35],[2,16],[3,21],[1,41],[5,22]]""", List.class);
        // 预期结果: [null,false,false,false,false,true,false,false,true,true,true,false,false,false,false,false,true,false,false,false,false]
        invoke(methods, parameters);
    }

    @Test
    public void test4() throws Exception {
        List<String> methods = this.objectMapper.readValue("""
                ["LockingTree","upgrade","lock","upgrade","unlock","upgrade","upgrade","lock","upgrade","upgrade","upgrade","lock","upgrade","lock","lock","upgrade","upgrade","upgrade","lock","upgrade","unlock"]""", List.class);
        List<List<Object>> parameters = this.objectMapper.readValue("""
                [[[-1,4,3,8,0,1,5,8,4,1]],[7,39],[3,9],[2,19],[3,9],[8,50],[0,37],[2,7],[2,7],[3,35],[4,25],[2,25],[6,7],[1,7],[1,21],[2,21],[8,47],[1,7],[3,30],[9,16],[0,5]]""", List.class);
        // 预期结果: [null,false,true,false,true,false,false,true,false,true,true,true,false,true,false,false,false,false,true,false,false]
        invoke(methods, parameters);
    }

    @Test
    public void test5() throws Exception {
        List<String> methods = this.objectMapper.readValue("""
                ["LockingTree","lock","upgrade","lock","upgrade","upgrade","lock","lock","upgrade","upgrade","lock","unlock","upgrade","upgrade","lock","unlock","upgrade","upgrade","upgrade","lock","upgrade","unlock","unlock","lock","upgrade","unlock","upgrade","unlock","upgrade","upgrade","upgrade","upgrade","upgrade","upgrade","upgrade","upgrade","lock","upgrade","upgrade","unlock","upgrade","upgrade","unlock","unlock","unlock","upgrade","upgrade","upgrade","upgrade","lock","upgrade","unlock","upgrade","upgrade","lock","upgrade","upgrade","upgrade","upgrade","upgrade","upgrade","upgrade","unlock","lock","lock","upgrade","lock","upgrade","upgrade","upgrade","upgrade","lock","unlock","upgrade","lock","unlock","upgrade","upgrade","unlock","unlock","lock","upgrade","upgrade","lock","upgrade","upgrade","lock","lock","upgrade","upgrade","upgrade","upgrade","upgrade","unlock","upgrade","unlock","upgrade","upgrade","unlock","lock","upgrade"]""", List.class);
        List<List<Object>> parameters = this.objectMapper.readValue("""
                [[[-1,36,21,22,5,41,23,0,14,15,26,24,41,33,13,22,4,16,0,22,7,6,18,8,0,13,24,26,15,37,18,18,13,40,4,28,18,0,8,24,18,30,19,34,24,22,19,20,27,26]],[35,3],[38,34],[35,73],[13,59],[12,71],[37,41],[16,33],[45,74],[38,46],[16,66],[8,78],[49,4],[41,99],[37,14],[37,41],[29,17],[4,85],[4,11],[16,31],[47,47],[46,44],[37,18],[43,58],[47,62],[16,33],[33,59],[35,3],[42,67],[9,10],[18,41],[18,67],[7,40],[20,32],[5,76],[25,71],[43,19],[26,68],[34,49],[43,58],[0,72],[38,66],[47,85],[1,69],[44,75],[32,5],[23,15],[23,87],[43,81],[36,15],[46,29],[36,15],[42,68],[1,83],[35,81],[44,27],[16,54],[47,54],[14,61],[0,11],[10,78],[10,79],[35,81],[38,42],[1,92],[31,55],[38,98],[19,13],[17,42],[10,77],[15,63],[1,5],[38,42],[44,1],[37,37],[1,92],[41,44],[16,53],[21,27],[21,90],[37,83],[26,33],[37,30],[37,52],[12,16],[37,74],[37,94],[15,54],[43,19],[1,54],[27,36],[44,62],[18,52],[37,37],[6,58],[36,89],[38,28],[41,41],[15,54],[44,82],[0,99]]""", List.class);
        invoke(methods, parameters);
    }

    @SuppressWarnings({"unchecked", "EnhancedSwitchMigration", "OptionalGetWithoutIsPresent", "ConstantConditions"})
    private void invoke(List<String> methods, List<List<Object>> parameters) throws Exception {
        List<Object> result = new LinkedList<>();
        OperationsOnTree.LockingTree test = null;
        for (int i = 0; i < methods.size(); i++) {
            String methodName = methods.get(i);
            switch (methodName) {
                case "LockingTree":
                    List<Integer> parameterObject = (List<Integer>) parameters.get(i).get(0);
                    int[] parameter = new int[parameterObject.size()];
                    for (int i1 = 0; i1 < parameterObject.size(); i1++) {
                        parameter[i1] = parameterObject.get(i1);
                    }
                    test = new OperationsOnTree.LockingTree(parameter);
                    result.add(null);
                    break;
                case "lock":
                case "unlock":
                case "upgrade":
                    Method method = Arrays.stream(OperationsOnTree.LockingTree.class.getMethods()).filter(m -> methodName.equals(m.getName())).findAny().get();
                    Object invoke = method.invoke(test, parameters.get(i).toArray());
                    result.add(invoke);
                    System.out.println(i + "\n" + "    " + methodName + "   " + parameters.get(i) + "\n    "
                            + Arrays.toString(Arrays.stream(test.lockArray).filter(a -> a.lockedChildren.size() > 0 || a.locked).toArray()));
                    break;
            }
        }
        System.out.println("[" + result.stream().map(i -> null == i ? "null" : i).map(Object::toString).collect(Collectors.joining(",")) + "]");
    }
}

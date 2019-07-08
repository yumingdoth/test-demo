# test-demo

## 单元测试demo
* 使用Mockito框架解决单元测试中的依赖
> Mock。简单地说就是对测试的类所依赖的其他类和对象，进行 mock － 构建它们的一个假的对象，定义这些假对象上的行为，然后提供给被测试对象使用。被测试对象像使用真的对象一样使用它们。用这种方式，我们可以把测试的目标限定于被测试对象本身，就如同在被测试对象周围做了一个划断，形成了一个尽量小的被测试目标。
* 使用内嵌的内存数据库对dao层进行单元测试

## 快速上手
1. SpringBoot进行单元测试
``` java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserTest {

    @Test
    public void userTest() {
        // test
    }
}
``` 
２. 使用内存数据库
``` java
@RunWith(SpringRunner.class)
@MybatisTest
@TestPropertySource(properties = {
        "spring.datasource.schema=classpath:schema.sql",
        "spring.datasource.data=classpath:data.sql"})
public class UserTest {

    @Test
    public void userTest() {
        // test
    }
}
```

３. 使用Mockito
* 简单例子
``` java
@Test
public void mockitoTest(){
    //生成一个mock对象
    List<String> mockedList = Mockito.mock(List.class);
    //打印mock对象的类名，看看mock对象为何物
    System.out.println(mockedList.getClass().getName());
    //操作mock对象
    mockedList.add("one");
    mockedList.get(0);
    mockedList.clear();
    //verify验证，mock对象是否发生过某些行为
    //如果验证不通过，Mockito会抛出异常
    Mockito.verify(mockedList).add("one");
    Mockito.verify(mockedList).get(0);
    //stub打桩，指定mock对象某些行为的返回，也就是我们常用的mock数据
    Mockito.when(mockedList.get(1)).thenReturn("13");
    //这里打印出“13”（但我们知道mockedList实际是没有下标为1的元素，这就是mock的功能）
    System.out.println(mockedList.get(1));
}
```
* 在service中使用
``` java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserService.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private UserDao userDao;

    @Test
    public void whenInvoke_thenReturn() {
        User user = new User();
        user.setName("Bob");
        user.setAge(0);

        // 1.设定mock对象期望的结果
        when(userDao.findByName("Bob")).thenReturn(user);
        // 2.调用被测试方法
        User bob = userService.findByName("Bob");
        // 3.验证测试结果
        assertEquals(user.getAge(), bob.getAge());
        // 验证findByName被调用过
        verify(userDao).findByName("Bob");
        // verify(userDao).findByName("Alice");
    }
}
```
* 使用静态导入
``` java
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
```

## Q&A
* 为什么要写单元测试？
方便自测，自动化测试

* 为什么使用内存数据库？　
不依赖本地数据库,不会对本地数据库造成影响
* 为什么使用mock
解决依赖问题
* `@spy` 和`＠mock`的区别？
Spy是有使用场景的，当我们需要mock一部分方法，而另外的方法需要正常执行时就需要用到Spy了，不然你就要mock全部用到的方法了
* `@InjectMocks`，你需要注入@Mock对象的对象，即`@InjectMocks`这个对象依赖其他mock对象。这个有点像依赖注入，它就是用来解决这个问题的。举个例子说明，ServiceB依赖ServiceA，你是需要测试ServiceB中的某个方法，但是这个方法依赖到ServiceA了，ServiceA的返回你不可控，你需要mock它，这时就要把@Mock作用于ServiceA，`@InjectMocks`作用于ServiceB。

## 工具类
* 读取json转换为对象
* 反射工具类，`org.springframework.test.util.ReflectionTestUtils`
``` java
ReflectionTestUtils.setField(upperObject, "fieldName", mockObject);把mock设置进去替换掉原来的对象。
```
*

```
Main reference documentation features:

mock()/@Mock: create mock
optionally specify how it should behave via Answer/MockSettings
when()/given() to specify how a mock should behave
If the provided answers don’t fit your needs, write one yourself extending the Answer interface
spy()/@Spy: partial mocking, real methods are invoked but still can be verified and stubbed
@InjectMocks: automatically inject mocks/spies fields annotated with @Spy or @Mock
verify(): to check methods were called with given arguments
can use flexible argument matching, for example any expression via the any()
or capture what arguments were called using @Captor instead
Try Behavior-Driven development syntax with BDDMockito
Use Mockito on Android, thanks to the team working on dexmaker
```

```
Remember
Do not mock types you don’t own
Don’t mock value objects
Don’t mock everything
Show love with your tests!
```
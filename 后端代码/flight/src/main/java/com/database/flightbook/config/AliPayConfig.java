package com.database.flightbook.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AliPayConfig {
    //商品appId
    public static String APPID="2021000118610504";
    //生成的私钥
    public static String RSA_PRIVATE_KEY="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQChlSu+ILlmHxqYuYnKSotLr6xsV90EkVL5mJC9eU2jUy0qBO+c/coRiZUTqFZH0nsJGg6jv6azcOzVUL7T0W0t8o0b6JqPCZRmXrr6Ma4Povyy9LxBjLl946peo9wn3UithwlY+TAWa+FDTyhye1HbkjHD91m1UKCs+PX7NdKMtwH4ifeF7tFs2IQcIaOlf9YlVXNGc/Jnv9kl61ef3lKRd/zgJFwybhtOHCcO2/NPVLrLUMutOl7WGF1u1D8/mcdPzh7oKMhAYfh4pIjnapBBW0kG0I79umobV/DbhRwIs9FKziVNH1Tc4IpPsIdK/UBZHnngXhpqmEAEZ7Idt3cHAgMBAAECggEAUKq2Ec8of7EbiA9omNMqQ3jKbbRXpMYgVHc0G0OJ3PXTYFzies/Ap4y2HgA2Qtjzj5GwqVMul/rSYZRUEE9HYF7sz3gKYUp5CbpVQj63FRtbaMQBj///Qrf8AA9yA3zXobn+kqqktSzaYs4L9UtXjHSp35mG8Zv0+yW3N9ig6ppL7neWAyFjOY03XEjOdKbxJlk56Hl6TJoxOH7R7vNbaXOEdj2ET27PB3hDIlOcxvtl5PLlTZMZ/xjUzddaYTuPwTtCmJuZ/5lSHLIw39cMEw2IFvAqvJRChsRdflSyXHYhwDusM+ZPFM0Um6Q6W+TnDU3zlb27gaLPDxfDVGJeoQKBgQD1q19hPwaKoWERonEwcPSyptCV4prFZSaFDy877y6ptnr1JdQg/8spTN047tT10Kp2hG8L67YysdKeaFifra+ZHNOyV1g1xn66XXFxGihYWwWv68+3wweB9Bt+aVZpKQFYgE5s8520Trs9vY3yF1HtZURteZeWEax0U4CbOQfVqQKBgQCoYJslSVZtHI49jgQOYj4x1Yoa7HvG+pU21e62magVK66xTQmWgGcVsqGjebCFgpt0vR5a7TBsSO5pG5OMeUitIvokzQu0XLk2RBp3CmZCJiNPLS9uFCHZR183gcRn+B8GxTrQj9HR6zVUA5k7jGd2jVf/0nnz8cbf4Pw6Tjl1LwKBgQDkx0XYgt/to+GIkZUE/nvyDwT6P//PyaW4je53ywLSixwwRZ0paexhNrJhPZDWpg7ItgXPvR98UDsKx7OYaB+5362JTGvaEFKd7T0ijbB6r7E5JcnXoGjSbWhZLXGKFuSo7TubnLcFXbhjA643f94MzJyPsocm+qh13/lJFDAmmQKBgQCkzwqNEJ+75TuFHMDzo03lUXMAr4DoXtY2+mfR680OC9fOS+2alsxP/K0iajZSOGkZdxODRK8lDq3ty8Q8t2pfreDSrLFMNYZEi5yr8E3vaY0eLshCIwqOtsGIf0hTzBXydpL/GgbyRhvIxEz0cJXFAMyl5srf8EQNVxcLqw2qmQKBgF9Z3Dd9/+mj8mDzwN+X0yJ8ZT7b8KdOPZME+3dBUTnjrMtu8yUiWRm4m9+yj/8+Oih7ZCFG+ZLPK+qGkKjfXq1Pti9TniQ/t4HM6HrX2Kv0xLvyx53F7Cz8aqApbshUZ6Tvo63/h+2moAZcPIm+HEGukf+etsGQwhLPIjKihWsQ";

    //服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，且需要公网地址(这里以百度为例)
    public static String notify_url="http://localhost:3000/#/";

    public static String return_url="http://localhost:3000/#/";
    //请求网关地址
    public static String URL="https://openapi.alipaydev.com/gateway.do";

    //编码
    public static String CHARSET="utf-8";

    public static String FORMAT="json";
    //支付宝公钥
    public static String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA19AmzP4hIH6O5RP7XrRWk6Dc4TW/z2eMZVwxm93iHxkO/a4MbushLKAdUNpnuKvKkqZoG2zGToiwpeMYIdx2jqQmxI1wxPS2xVahtKZRcuOrnUs4y9bkg+dLmxpntQRRShx2aiPHP7ek315C3cPlHec5rFjfNed2l3YewWm4RFiIr/2ugvhRRp9df08ZQLesbCIybg2i0vGwCkvQnclOjwoV7H4y/DQ8AG3P1HZIypXaoCyA6GJQvf3t2/YyDB3Y70BHit6h7z6HUl7FF0J7JdUPB8Ah+q1vMAovLC8XNByxCP1iI/7Wl6RE/TW77jbdC7P/iR9OAkswrqBU5hpiywIDAQAB";
    //RSA2
    public static String SIGNTYPE="RSA2";

}

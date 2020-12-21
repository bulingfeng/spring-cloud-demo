## consul的登录地址
```aidl
http://127.0.0.1:8500/ui/dc1/kv
consul.io/docs/dynamic-app-config/sessions
```

## 参考文档
```
https://www.cnblogs.com/mrhelloworld/p/consul-config.html
```


## 注意事项
> 比如配置的是dev环境，但是default-context这个默认配置一个域，如果dev中配置参数少于默认域则会加载默认域中的值。如果都有对应的key值，则是
> 按照dev环境进行配置。所以一定要保证生产和开发的key值保持一致。
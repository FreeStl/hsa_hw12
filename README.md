## First test: different eviction strategies
1) Create config files redis and redis sentinal. I will use 1 master - 2 slaves architecture. Write to master, read from slaves.
in redis.config we can specify different eviction strategies.
2) Create docker compose file, according to the architecture above. Use sentinel.config to configure sentinel.
Use any redis client to test different eviction strategies.
3) Write autotest, that will create load on the redis cluster. We should reach memory limit, so some keys will be evicted.

## First test: different eviction strategies
Add another step:
4) Write proxy app, that will be in change of probabilistic cache eviction.
In the example app, there is an endpoint 'GET /api/{id}'. 


How it works:
If cache hit, check key ttl with special formula. If formula returns true - it means, that we need to refresh key.
Formula has random parameter, which helps to avoid the situation, when multiple threads try to evict the cache simultaneously.
When we insert key again - we specify delta parameter (how long it took us to get cache from persistent db, for example),
and ttl parameter (time, when key should available for eviction). We use these values when we refresh the key.



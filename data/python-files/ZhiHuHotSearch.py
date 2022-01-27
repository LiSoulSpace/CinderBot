import aiohttp
import asyncio
import os
import sys
filePath = os.path.dirname(os.path.abspath(__file__))
sys.path.append(filePath)
import util_main


async def get_zhihu_hot():
    zhihu_hot_url = "https://www.zhihu.com/api/v3/feed/topstory/hot-lists/total?limit=50&desktop=true"
    async with aiohttp.ClientSession() as session:
        async with session.get(url=zhihu_hot_url) as resp:
            data = await resp.json()
    data = data["data"]
    text_list = ["知乎实时热榜:"]
    index = 0
    for i in data:
        index += 1
        text_list.append("\n%d. %s" % (index, i["target"]["title"]))
    text = "".join(text_list).replace("#", "")
    await util_main.text_to_img(message=text, save_name="ZhiHuHotSearch.png")


if __name__ == '__main__':
    loop = asyncio.get_event_loop()
    loop.run_until_complete(get_zhihu_hot())

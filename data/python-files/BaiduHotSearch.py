import asyncio
import re
import aiohttp

from lxml import etree
from lxml.html import tostring
import html as html_main
import os
import sys

filePath = os.path.dirname(os.path.abspath(__file__))
sys.path.append(filePath)
import util_main


async def baiduHotSearchGet():
    async with aiohttp.ClientSession() as session:
        url = "https://top.baidu.com/board?tab=realtime"
        async with session.get(url) as response:
            html = await response.text()
            f = open("baiduSearch.html", "w+")
            f.write(html)
            f.close()
            html = etree.HTML(html)
            t = []
            for i in range(1, 31):
                t.append(html.xpath(f'/html/body/div/div/main/div[2]/div/div[2]/div[{i}]/div[2]/a/div[1]')[0])
            answers = []
            for i in t:
                t = html_main.unescape(str(tostring(i)))
                s = re.findall(r'> (.*)<', t)
                answers.append(s[0])
            print(answers)
            final_str = ""
            for i in answers[1:]:
                final_str = final_str + i + '\n'
            final_str = final_str[:-1]
            await util_main.text_to_img(message=final_str, save_name="BaiduHotSearch.png")


if __name__ == '__main__':
    loop = asyncio.get_event_loop()
    loop.run_until_complete(baiduHotSearchGet())

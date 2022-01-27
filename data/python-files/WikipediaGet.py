import argparse
import asyncio
import aiohttp
import re
import os
import sys
from lxml import etree
from lxml.html import tostring
import html as html_main
filePath = os.path.dirname(os.path.abspath(__file__))
sys.path.append(filePath)
import util_main


async def GetFromWikipedia(main_str: str):
    async with aiohttp.ClientSession() as session:
        s = "https://zh.wiki.sxisa.org/zh-cn/" + main_str
        async with session.get(s) as response:
            html = await response.text()
            html = etree.HTML(html)
            t = html.xpath('//*[@id="mw-content-text"]/div[1]')
            all_str = ""
            for i in t[0]:
                if i.tag == "p":
                    all_str += str(tostring(i))
                if i.tag == "h2":
                    break
            all_str = re.sub(r'<.*?>', '', all_str)
            all_str = re.sub(r'\[.*?\]', '', all_str)
            all_str = html_main.unescape(all_str)
            all_str = re.sub('\n', '', all_str)
            all_str = all_str.replace("\\n", '')
            print(all_str)
            all_str = all_str.replace("b'", '')
            all_str = all_str.replace('\'', '')
            print(all_str)
            return all_str


async def parseResultFromWikipedia(main_str: str):
    result = await GetFromWikipedia(main_str)
    if result == "":
        result = "抱歉，可能没有找到结果。"
    await util_main.text_to_img(message=result, save_name="WikipediaResult.png")

if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument("keyword", type=str,
                        help="keyword to search in wikipedia")
    args = parser.parse_args()
    print(args.keyword)
    loop = asyncio.get_event_loop()
    loop.run_until_complete(parseResultFromWikipedia(args.keyword))

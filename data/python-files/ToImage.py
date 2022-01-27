import argparse
import asyncio
import os
import sys

filePath = os.path.dirname(os.path.abspath(__file__))
sys.path.append(filePath)
import util_main


async def parseTextToImg():
    f = open("./poems.txt")
    main_str = f.read()
    f.close()
    if main_str == "":
        main_str = "可能没有找到结果。"
    await util_main.text_to_img(message=main_str, save_name="poems.png")


if __name__ == '__main__':
    loop = asyncio.get_event_loop()
    loop.run_until_complete(parseTextToImg())

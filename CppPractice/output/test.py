import pyautogui as p
#載入套件做之後的輸入法檢驗
import time as t
#載入這個套件讓他可以有延遲時間
list1 = "72 75 70 20 77 75 30 20 6a 30 33 67 3b 34 32 6c 34 77 6a 36 67  6a 20 65 6a 30 33 78 39 36 35 6c 33 6a 69 33".split() #將平台上的數字穿複製成字串用split去切割成list
   
print(list1)
list2 = []
for i in range(len(list1)):
    list2.append(int(list1[i],16))
#將16進位的數字轉成十進位加入list2

str1 = ""
print(list2)
for i in list2:
    str1 += chr(i)
#將陣列裡面的東西十進位ascii code轉到文字加到str1裡面

t.sleep(5)
#時間延遲，讓我們可以把游標移動到我們要輸出明文的位置
for i in str1:
    p.press(i)
#利用迴圈去讓電腦按我們要的按鈕
package com.example.mdpiyalhasan.speacktotext;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by Md Piyal Hasan on 11/13/2017.
 */
public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        if(bundle==null){
            Object[] pdus= (Object[]) bundle.get("pdus");
            SmsMessage smsMessage=SmsMessage.createFromPdu((byte[]) pdus[0]);
            Intent intent1=new Intent(context,Controller.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent1.putExtra("smsbody","piyal");
            context.startActivity(intent1);
            Toast.makeText(context,smsMessage.getMessageBody(),Toast.LENGTH_SHORT).show();
        }
    }
}

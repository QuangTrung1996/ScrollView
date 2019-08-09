




public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "SampleApp";

    private String[] permissions =
    {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Check permissions
        Permissions.check(this, permissions, new PermissionHandler()
        {
            @Override
            public void onGranted()
            {
                setContentView(R.layout.activity_main);
            }

            @Override
            public void onDenied(ArrayList<String> deniedPermissions)
            {
                Log.d(TAG, "onDenied: " + deniedPermissions.size());
            }
        });
    }
}

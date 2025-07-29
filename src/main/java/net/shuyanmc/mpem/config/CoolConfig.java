package net.shuyanmc.mpem.config;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class CoolConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    // ==================== Entity Optimization ====================
    public static ModConfigSpec.BooleanValue disableEntityCollisions;
    public static ModConfigSpec.BooleanValue optimizeEntityAI;
    public static ModConfigSpec.IntValue entityActivationRange;
    public static final ModConfigSpec.BooleanValue OPTIMIZE_ENTITY_CLEANUP;
    public static final ModConfigSpec.BooleanValue reduceEntityUpdates;

    // Entity optimization settings
    public static ModConfigSpec.BooleanValue optimizeEntities;
    public static ModConfigSpec.BooleanValue ignoreDeadEntities;
    public static ModConfigSpec.BooleanValue optimizeItems;
    public static ModConfigSpec.ConfigValue<List<? extends String>> itemWhitelist;
    public static ModConfigSpec.IntValue horizontalRange;
    public static ModConfigSpec.IntValue verticalRange;
    public static ModConfigSpec.BooleanValue tickRaidersInRaid;

    // ==================== Item Optimization ====================
    public static ModConfigSpec.IntValue maxStackSize;
    public static ModConfigSpec.DoubleValue mergeDistance;
    public static ModConfigSpec.IntValue listMode;
    public static ModConfigSpec.ConfigValue<List<? extends String>> itemList;
    public static ModConfigSpec.BooleanValue showStackCount;
    public static final ModConfigSpec.BooleanValue ENABLED;
    public static final ModConfigSpec.IntValue MAX_STACK_SIZE;

    // ==================== Memory Optimization ====================
    public static final ModConfigSpec.IntValue MEMORY_CLEAN_INTERVAL;
    public static final ModConfigSpec.BooleanValue ENABLE_GC;

    // ==================== Debug Options ====================
    public static final ModConfigSpec.BooleanValue DEBUG_LOGGING;
    public static final ModConfigSpec.BooleanValue LOG_BLOCK_EVENTS;

    // ==================== Chunk Optimization ====================
    public static ModConfigSpec.BooleanValue aggressiveChunkUnloading;
    public static ModConfigSpec.IntValue chunkUnloadDelay;
    public static final ModConfigSpec.BooleanValue reduceChunkUpdates;
    public static final ModConfigSpec.BooleanValue filterRedundantBlockUpdates;
    public static final ModConfigSpec.IntValue CHUNK_GEN_THREADS;

    // ==================== Async Optimization ====================
    public static final ModConfigSpec.BooleanValue ASYNC_PARTICLES;
    public static final ModConfigSpec.IntValue ASYNC_PARTICLES_THREADS;
    public static final ModConfigSpec.IntValue AI_THREADS;
    public static final ModConfigSpec.IntValue MAX_ASYNC_OPERATIONS_PER_TICK;
    public static final ModConfigSpec.BooleanValue DISABLE_ASYNC_ON_ERROR;
    public static final ModConfigSpec.IntValue ASYNC_EVENT_TIMEOUT;
    public static final ModConfigSpec.BooleanValue WAIT_FOR_ASYNC_EVENTS;
    public static ModConfigSpec.IntValue maxCPUPro;
    public static ModConfigSpec.IntValue maxthreads;

    // ==================== Event System ====================
    public static final ModConfigSpec.BooleanValue ENABLE_EVENT_OPTIMIZATION;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> ASYNC_EVENT_CLASS_BLACKLIST;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> ASYNC_EVENT_MOD_BLACKLIST;
    public static final ModConfigSpec.BooleanValue STRICT_CLASS_CHECKING;

    // ==================== Anti-Cheat System ====================
    public static final ModConfigSpec.BooleanValue ANTI_CHEAT_ENABLED;
    public static final ModConfigSpec.IntValue DETECTION_DELAY;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> CLIENT_CLASS_WHITELIST;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> MOD_WHITELIST;
    public static final ModConfigSpec.BooleanValue CLASS_NAME_DETECTION_ENABLED;

    // ==================== Frame Rate Control ====================
    public static final ModConfigSpec.BooleanValue REDUCE_FPS_WHEN_INACTIVE;
    public static final ModConfigSpec.IntValue INACTIVE_FPS_LIMIT;

    // ==================== Render Distance ====================
    public static final ModConfigSpec.BooleanValue REDUCE_RENDER_DISTANCE_WHEN_INACTIVE;
    public static final ModConfigSpec.IntValue INACTIVE_RENDER_DISTANCE;



    static {
        // ==================== ʵ���Ż����� | Entity Optimization Settings ====================
        BUILDER.comment("ʵ���Ż����� | Entity Optimization Settings").push("entity_optimization");

        disableEntityCollisions = BUILDER
                .comment("�Ż�ʵ����ײ��� | Optimize entity collision detection")
                .define("disableEntityCollisions", true);

        optimizeEntityAI = BUILDER
                .comment("�Ż�ʵ��AI���� | Optimize entity AI calculations")
                .define("optimizeEntityAI", true);

        entityActivationRange = BUILDER
                .comment("ʵ�弤�Χ (����) | Entity activation range (blocks)")
                .defineInRange("entityActivationRange", 32, 16, 128);

        OPTIMIZE_ENTITY_CLEANUP = BUILDER
                .comment("��������ʵ������ | Enable dead entity cleanup")
                .define("entityCleanup", true);

        reduceEntityUpdates = BUILDER
                .comment("����Զ��ʵ���ͬ��Ƶ�� | Reduce entity sync frequency for distant entities")
                .define("reduceEntityUpdates", true);

        // Entity optimization settings
        optimizeEntities = BUILDER
                .comment("�Ƿ��Ż�ʵ��tick | Optimize entity ticking")
                .define("optimizeEntities", true);

        ignoreDeadEntities = BUILDER
                .comment("�Ƿ������������ʵ�� | Ignore dead entities")
                .define("ignoreDeadEntities", false);

        optimizeItems = BUILDER
                .comment("�Ƿ��Ż���Ʒʵ�� | Optimize item entities")
                .define("optimizeItems", true);

        itemWhitelist = BUILDER
                .comment("��Ʒ�����������ᱻ�Ż�����Ʒ��| Item whitelist (items that will not be optimized)")
                .defineList("itemWhitelist", Collections.singletonList("minecraft:nether_star"),
                        o -> o instanceof String);

        horizontalRange = BUILDER
                .comment("ʵ�弤�Χ��ˮƽ����| Horizontal activation range for entities (blocks)")
                .defineInRange("horizontalRange", 32, 8, 128);

        verticalRange = BUILDER
                .comment("ʵ�弤�Χ����ֱ����| Vertical activation range for entities (blocks)")
                .defineInRange("verticalRange", 16, 8, 64);

        tickRaidersInRaid = BUILDER
                .comment("��Ϯ�����Ƿ�ǿ��tickϮ���� | Force tick raiders during raids")
                .define("tickRaidersInRaid", true);

        BUILDER.pop();

        // ==================== ֡�ʿ������� | Frame Rate Control Settings ====================
        BUILDER.comment("֡�ʿ������� | Frame Rate Control Settings").push("frame_rate");

        REDUCE_FPS_WHEN_INACTIVE = BUILDER
                .comment("�Ƿ��ڴ��ڷǻʱ����FPS | Reduce FPS when window is inactive")
                .define("reduceFpsWhenInactive", true);

        INACTIVE_FPS_LIMIT = BUILDER
                .comment("�ǻ״̬ʱ��FPS���� | FPS limit when inactive")
                .defineInRange("inactiveFpsLimit", 30, 5, 120);

        BUILDER.pop();

        // ==================== ��Ⱦ�������� | Render Distance Settings ====================
        BUILDER.comment("��Ⱦ�������� | Render Distance Settings").push("render_distance");

        REDUCE_RENDER_DISTANCE_WHEN_INACTIVE = BUILDER
                .comment("�Ƿ��ڴ��ڷǻʱ������Ⱦ���� | Reduce render distance when window is inactive")
                .define("reduceRenderDistanceWhenInactive", true);

        INACTIVE_RENDER_DISTANCE = BUILDER
                .comment("�ǻ״̬ʱ����Ⱦ���� | Render distance when inactive")
                .defineInRange("inactiveRenderDistance", 4, 2, 32);

        BUILDER.pop();



        // ==================== ��Ʒ�Ż����� | Item Optimization Settings ====================
        BUILDER.comment("��Ʒ�Ż����� | Item Optimization Settings").push("item_optimization");

        maxStackSize = BUILDER
                .comment("�ϲ���Ʒ�����ѵ�������0��ʾ�����ƣ�| Maximum stack size for merged items (0 = no limit)")
                .defineInRange("maxStackSize", 0, 0, Integer.MAX_VALUE);

        mergeDistance = BUILDER
                .comment("��Ʒ�ϲ����뾶����λ�����飩| Item merge detection radius in blocks")
                .defineInRange("mergeDistance", 0.5, 0.1, 10.0);

        listMode = BUILDER
                .comment("0: ���� 1: ������ģʽ 2: ������ģʽ | 0: Disabled, 1: Whitelist, 2: Blacklist")
                .defineInRange("listMode", 0, 0, 2);

        itemList = BUILDER
                .comment("������/�������е���Ʒע�����б� | Item registry names for whitelist/blacklist")
                .defineList("itemList", Collections.emptyList(), o -> o instanceof String);

        showStackCount = BUILDER
                .comment("�Ƿ��ںϲ������Ʒ����ʾ�ѵ����� | Whether to show stack count on merged items")
                .define("showStackCount", true);

        BUILDER.push("stack_size");

        ENABLED = BUILDER
                .comment("�����Զ���ѵ���С | Enable custom stack sizes")
                .define("enabled", true);

        MAX_STACK_SIZE = BUILDER
                .comment("�����Ʒ�ѵ���С (1-9999) | Maximum item stack size (1-9999)")
                .defineInRange("maxStackSize", 64, 1, 9999);

        BUILDER.pop();
        BUILDER.pop();

        // ==================== �ڴ��Ż����� | Memory Optimization Settings ====================
        BUILDER.comment("�ڴ��Ż����� | Memory Optimization Settings").push("memory_optimization");

        MEMORY_CLEAN_INTERVAL = BUILDER
                .comment("�ڴ��������(��) | Memory cleanup interval (seconds)")
                .defineInRange("cleanInterval", 300, 60, 3600);

        ENABLE_GC = BUILDER
                .comment("�Ƿ�������ʱ������������ | Whether to trigger garbage collection during cleanup")
                .define("enableGC", true);

        BUILDER.pop();

        // ==================== �������� | Debug Settings ====================
        BUILDER.comment("�������� | Debug Settings").push("debug");

        DEBUG_LOGGING = BUILDER
                .comment("���õ�����־ | Enable debug logging")
                .define("debug", false);

        LOG_BLOCK_EVENTS = BUILDER
                .comment("��¼��������¼� | Log block related events")
                .define("logBlockEvents", false);

        BUILDER.pop();

        // ==================== �����Ż����� | Chunk Optimization Settings ====================
        BUILDER.comment("�����Ż����� | Chunk Optimization Settings").push("chunk_optimization");

        aggressiveChunkUnloading = BUILDER
                .comment("����ж�طǻ���� | Aggressively unload inactive chunks")
                .define("aggressiveChunkUnloading", true);

        chunkUnloadDelay = BUILDER
                .comment("����ж���ӳ� (��) | Chunk unload delay (seconds)")
                .defineInRange("chunkUnloadDelay", 60, 10, 600);

        reduceChunkUpdates = BUILDER
                .comment("������ƶ��̾���ʱ�����������Ƶ�� | Reduce chunk update frequency when player moves short distances")
                .define("reduceChunkUpdates", true);

        filterRedundantBlockUpdates = BUILDER
                .comment("��������ķ���������ݰ� | Filter redundant block update packets")
                .define("filterRedundantBlockUpdates", true);

        CHUNK_GEN_THREADS = BUILDER
                .comment("�첽�������ɵ��߳��� | Number of threads for async chunk generation")
                .defineInRange("chunkGenThreads", 2, 1, 8);

        BUILDER.pop();

        // ==================== �첽�Ż����� | Async Optimization Settings ====================
        BUILDER.comment("�첽�Ż����� | Async Optimization Settings").push("async_optimization");

        ASYNC_PARTICLES = BUILDER
                .comment("�����첽���Ӵ��� | Enable asynchronous particle processing")
                .define("asyncParticles", true);

        ASYNC_PARTICLES_THREADS = BUILDER
                .comment("�����첽���ӵ��߳��� (�Ƽ�1-4) | Number of threads to use for async particles (1-4 recommended)")
                .defineInRange("asyncParticlesThreads", 2, 1, Runtime.getRuntime().availableProcessors());

        AI_THREADS = BUILDER
                .comment("AI�������߳��� | Number of threads for AI processing")
                .defineInRange("aiThreads", 2, 1, 8);

        MAX_ASYNC_OPERATIONS_PER_TICK = BUILDER
                .comment("ÿtick����������첽������ | Max async operations processed per tick")
                .defineInRange("maxAsyncOpsPerTick", 1000, 100, 10000);

        DISABLE_ASYNC_ON_ERROR = BUILDER
                .comment("��������ø��¼����͵��첽���� | Disable async for event type after errors")
                .define("disableAsyncOnError", true);

        ASYNC_EVENT_TIMEOUT = BUILDER
                .comment("�첽�¼���ʱʱ��(��) | Timeout in seconds for async events")
                .defineInRange("asyncEventTimeout", 2, 1, 10);

        WAIT_FOR_ASYNC_EVENTS = BUILDER
                .comment("�ȴ��첽�¼���� | Wait for async events to complete")
                .define("waitForAsyncEvents", false);

        BUILDER.push("async_cpu_config");

        maxCPUPro = BUILDER
                .comment("�첽ϵͳ���CPU������ | Max CPU Cores for async system (only for async threads, not world async)")
                .defineInRange("maxCPUPro", 2, 2, 9999);

        maxthreads = BUILDER
                .comment("����߳��� | Max Threads (only for general async threads, not dedicated async threads)")
                .defineInRange("maxthreads", 2, 2, 9999);

        BUILDER.pop();
        BUILDER.pop();

        // ==================== �¼�ϵͳ���� | Event System Settings ====================
        BUILDER.comment("�¼�ϵͳ���� | Event System Settings").push("event_system");

        ENABLE_EVENT_OPTIMIZATION = BUILDER
                .comment("���ø������¼�ϵͳ�Ż� | Enable High-Performance event system optimization")
                .define("enableOptimization", true);

        ASYNC_EVENT_CLASS_BLACKLIST = BUILDER
                .comment("��Ӧ�첽�������¼����б� | List of event classes that should NOT be processed asynchronously",
                        "֧��ͨ��� (�� 'net.minecraftforge.event.entity.living.*') | Supports wildcards (e.g. 'net.minecraftforge.event.entity.living.*')")
                .defineList("classBlacklist",
                        List.of(
                                "net.minecraftforge.event.TickEvent",
                                "net.minecraftforge.event.level.LevelTickEvent",
                                "net.minecraftforge.event.entity.living.*"
                        ),
                        o -> o instanceof String);

        ASYNC_EVENT_MOD_BLACKLIST = BUILDER
                .comment("��Ӧ�첽������ģ��ID�б� | List of mod IDs whose events should NOT be processed asynchronously")
                .defineList("modBlacklist", Collections.emptyList(), o -> o instanceof String);

        STRICT_CLASS_CHECKING = BUILDER
                .comment("�����ϸ������ڼ�� (�Ƽ�Ϊ�ȶ���) | Enable strict class existence checking (recommended for stability)")
                .define("strictClassChecking", true);

        BUILDER.pop();

        // ==================== ������ϵͳ���� | Anti-Cheat System Settings ====================
        BUILDER.comment("������ϵͳ���� (����������Ч) | Anti-Cheat System Settings (Server Only)").push("anti_cheat");

        ANTI_CHEAT_ENABLED = BUILDER
                .comment("�Ƿ����÷�����ϵͳ | Whether to enable anti-cheat system")
                .define("enabled", true);

        DETECTION_DELAY = BUILDER
                .comment("����ӳ�ʱ�䣨�룩 | Detection delay (seconds)",
                        "����ֵ3-5�룬�����¼ʱ��ͻ | Recommended 3-5 seconds to avoid login conflicts")
                .defineInRange("detectionDelay", 3, 1, 10);

        CLASS_NAME_DETECTION_ENABLED = BUILDER
                .comment("�Ƿ�����������ȫ��� | Whether to enable class name security detection")
                .define("classNameDetectionEnabled", false);

        CLIENT_CLASS_WHITELIST = BUILDER
                .comment("�ͻ������������� (֧��ͨ���) | Client class name whitelist (supports wildcards)")
                .defineList("clientClassWhitelist",
                        List.of(
                                "com.example.safemod.*",
                                "net.shuyanmc.mpem.*"
                        ),
                        o -> o instanceof String);

        MOD_WHITELIST = BUILDER
                .comment("����ģ��ID�б� | Exempt mod ID list")
                .defineList("modWhitelist", Collections.emptyList(), o -> o instanceof String);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    // ==================== ���߷��� | Utility Methods ====================
    public static Set<String> getAsyncEventClassBlacklist() {
        return new HashSet<>(ASYNC_EVENT_CLASS_BLACKLIST.get());
    }

    public static boolean isDetectionEnabled() {
        return CLASS_NAME_DETECTION_ENABLED.get();
    }

    public static Set<String> getAsyncEventModBlacklist() {
        return new HashSet<>(ASYNC_EVENT_MOD_BLACKLIST.get());
    }

    public static boolean isStrictClassCheckingEnabled() {
        return STRICT_CLASS_CHECKING.get();
    }

    public static Set<String> getClientClassWhitelist() {
        return new HashSet<>(CLIENT_CLASS_WHITELIST.get());
    }

    public static Set<String> getModWhitelist() {
        return new HashSet<>(MOD_WHITELIST.get());
    }
}